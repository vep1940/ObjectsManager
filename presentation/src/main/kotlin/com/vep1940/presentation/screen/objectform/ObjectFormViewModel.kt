package com.vep1940.presentation.screen.objectform

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vep1940.domain.usecase.AddObject
import com.vep1940.domain.usecase.GetObject
import com.vep1940.domain.usecase.ModifyObject
import com.vep1940.presentation.screen.objectform.model.ObjectFormAction
import com.vep1940.presentation.screen.objectform.model.ObjectFormDisplay
import com.vep1940.presentation.screen.objectform.model.ObjectFormState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ObjectFormViewModel(
    private val getObject: GetObject,
    private val addObject: AddObject,
    private val modifyObject: ModifyObject,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val initialArgs = ObjectFormArgs(savedStateHandle)
    private val modifyingObject = initialArgs.objectId != null

    private val _display = MutableStateFlow<ObjectFormState>(ObjectFormState.Loading)
    val display: StateFlow<ObjectFormState> = _display

    init {
        initialArgs.objectId?.let {
            viewModelScope.launch {
                val item = getObject(objectId = it)
                _display.update {
                    ObjectFormState.Success(
                        display = ObjectFormDisplay(
                            name = item.name,
                            hasNameError = false,
                            description = item.description,
                            type = item.type,
                            hasTypeError = false,
                        )
                    )
                }
            }
        } ?: run {
            _display.update {
                ObjectFormState.Success(
                    display = ObjectFormDisplay(
                        name = "",
                        hasNameError = true,
                        description = "",
                        type = "",
                        hasTypeError = true,
                    )
                )
            }
        }
    }

    fun onAction(action: ObjectFormAction) {
        when (action) {
            is ObjectFormAction.DescriptionChange -> onDescriptionChange(action.value)
            is ObjectFormAction.NameChange -> onNameChange(action.value)
            is ObjectFormAction.TypeChange -> onTypeChange(action.value)
            ObjectFormAction.SaveObject -> if (modifyingObject) {
                processModify()
            } else {
                processAdd()
            }
        }
    }

    private fun onNameChange(value: String) {
        getDisplay()?.let {
            onFieldChange(it.copy(name = value, hasNameError = value.isBlank()))
        }
    }

    private fun onDescriptionChange(value: String) {
        getDisplay()?.let {
            onFieldChange(it.copy(description = value))
        }
    }

    private fun onTypeChange(value: String) {
        getDisplay()?.let {
            onFieldChange(it.copy(type = value, hasTypeError = value.isBlank()))
        }
    }

    private fun onFieldChange(newState: ObjectFormDisplay) {
        viewModelScope.launch {
            _display.update { _ -> ObjectFormState.Success(newState) }
        }
    }

    private fun processAdd() {
        viewModelScope.launch {
            getDisplay()?.let {
                addObject(name = it.name, description = it.description, type = it.type)
            }
        }
    }

    private fun processModify() {
        viewModelScope.launch {
            val display = getDisplay()
            if (display != null && initialArgs.objectId != null) {
                modifyObject(
                    id = initialArgs.objectId,
                    name = display.name,
                    description = display.description,
                    type = display.type
                )
            }
        }
    }

    private fun getDisplay() = (display.value as? ObjectFormState.Success)?.display
}