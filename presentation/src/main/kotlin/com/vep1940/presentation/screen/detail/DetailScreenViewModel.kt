package com.vep1940.presentation.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vep1940.domain.usecase.DeleteRelation
import com.vep1940.domain.usecase.GetDetailedObject
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
            is DetailScreenAction.DeleteRelation -> processDeleteRelation(action)
            else -> { /*NOTHING TO DO HERE*/
            }
        }
    }

    private fun processDeleteRelation(action: DetailScreenAction.DeleteRelation) {
        viewModelScope.launch {
            deleteRelation(objectId1 = action.objectId1, objectId2 = action.objectId2)
        }
    }
}