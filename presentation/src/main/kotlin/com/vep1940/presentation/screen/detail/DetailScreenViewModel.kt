package com.vep1940.presentation.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vep1940.domain.usecase.AddRelation
import com.vep1940.domain.usecase.DeleteRelation
import com.vep1940.domain.usecase.GetDetailedObject
import com.vep1940.domain.usecase.ModifyObject
import com.vep1940.domain.usecase.ModifyRelation
import com.vep1940.presentation.model.toPresentation
import com.vep1940.presentation.screen.detail.model.DetailScreenAction
import com.vep1940.presentation.screen.detail.model.DetailScreenDisplay
import com.vep1940.presentation.screen.detail.model.DetailScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailScreenViewModel(
    private val getDetailedObject: GetDetailedObject,
    private val modifyObject: ModifyObject,
    private val addRelation: AddRelation,
    private val modifyRelation: ModifyRelation,
    private val deleteRelation: DeleteRelation,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val initialArgs = DetailScreenArgs(savedStateHandle)

    private val _display = MutableStateFlow<DetailScreenState>(DetailScreenState.Loading)
    val display: StateFlow<DetailScreenState> = _display

    init {
        viewModelScope.launch {
            getDetailedObject(initialArgs.objectId)
                .map {
                    DetailScreenState.Success(DetailScreenDisplay(it.toPresentation()))
                }
                .catch { DetailScreenState.Error }
                .collect { screenState -> _display.update { screenState } }
        }
    }

    fun onAction(action: DetailScreenAction) {
        when (action) {
            is DetailScreenAction.ModifyObject -> processModify(action)
            is DetailScreenAction.AddRelation -> processAddRelation(action)
            is DetailScreenAction.ModifyRelation -> processModifyRelation(action)
            is DetailScreenAction.DeleteRelation -> processDeleteRelation(action)
        }
    }

    private fun processModify(action: DetailScreenAction.ModifyObject) {
        viewModelScope.launch {
            modifyObject(
                id = action.id,
                name = action.name,
                description = action.description,
                type = action.type,
            )
        }
    }

    private fun processAddRelation(action: DetailScreenAction.AddRelation) {
        viewModelScope.launch {
            addRelation(objectId1 = action.objectId1, objectId2 = action.objectId2)
        }
    }

    private fun processModifyRelation(action: DetailScreenAction.ModifyRelation) {
        viewModelScope.launch {
            modifyRelation(
                oldObjectId1 = action.oldObjectId1,
                oldObjectId2 = action.oldObjectId2,
                newObjectId1 = action.newObjectId1,
                newObjectId2 = action.newObjectId2,
            )
        }
    }

    private fun processDeleteRelation(action: DetailScreenAction.DeleteRelation) {
        viewModelScope.launch {
            deleteRelation(objectId1 = action.objectId1, objectId2 = action.objectId2)
        }
    }
}