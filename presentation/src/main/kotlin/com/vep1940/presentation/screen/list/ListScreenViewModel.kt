package com.vep1940.presentation.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vep1940.domain.model.Object
import com.vep1940.domain.usecase.DeleteObject
import com.vep1940.domain.usecase.GetObjects
import com.vep1940.presentation.model.toPresentation
import com.vep1940.presentation.screen.list.model.ListScreenAction
import com.vep1940.presentation.screen.list.model.ListScreenDisplay
import com.vep1940.presentation.screen.list.model.ListScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListScreenViewModel(
    private val getObjects: GetObjects,
    private val deleteObject: DeleteObject,
) : ViewModel() {

    private val _display = MutableStateFlow<ListScreenState>(ListScreenState.Loading)
    val display: StateFlow<ListScreenState> = _display

    init {
        viewModelScope.launch {
            getObjects()
                .map<List<Object>, ListScreenState> { ListScreenState.Success(ListScreenDisplay(it.toPresentation())) }
                .catch { ListScreenState.Error }
                .collect { screenState -> _display.update { screenState } }
        }
    }

    fun onAction(action: ListScreenAction) {
        when (action) {
            is ListScreenAction.DeleteObject -> processDelete(action)
            else -> { /* NOTHING TO DO HERE */
            }
        }
    }

    private fun processDelete(action: ListScreenAction.DeleteObject) {
        viewModelScope.launch {
            deleteObject(id = action.id)
        }
    }
}