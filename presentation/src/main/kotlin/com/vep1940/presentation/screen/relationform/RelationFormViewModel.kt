package com.vep1940.presentation.screen.relationform

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vep1940.domain.usecase.AddRelation
import com.vep1940.domain.usecase.GetObject
import com.vep1940.domain.usecase.GetPossibleRelations
import com.vep1940.domain.usecase.ModifyRelation
import com.vep1940.presentation.model.toPresentation
import com.vep1940.presentation.screen.relationform.model.RelationFormAction
import com.vep1940.presentation.screen.relationform.model.RelationFormDisplay
import com.vep1940.presentation.screen.relationform.model.RelationFormState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RelationFormViewModel(
    private val getObject: GetObject,
    private val getPossibleRelations: GetPossibleRelations,
    private val addRelation: AddRelation,
    private val modifyRelation: ModifyRelation,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val initialArgs = RelationFormArgs(savedStateHandle)
    private val modifyingRelation = initialArgs.relatedObjectId != null

    private val _display = MutableStateFlow<RelationFormState>(RelationFormState.Loading)
    val display: StateFlow<RelationFormState> = _display

    init {
        viewModelScope.launch {
            val relatedObject = initialArgs.relatedObjectId?.let { getObject(it) }
            val possibleRelations = getPossibleRelations(initialArgs.objectId)
            val finalPossibleRelations = if (relatedObject != null) {
                possibleRelations + relatedObject
            } else {
                possibleRelations
            }
            _display.update {
                RelationFormState.Success(
                    display = RelationFormDisplay(
                        possibleRelations = finalPossibleRelations.toPresentation(),
                        selectedRelation = relatedObject?.toPresentation(),
                    )
                )
            }
        }
    }

    fun onAction(action: RelationFormAction) {
        when (action) {
            is RelationFormAction.RelationChange -> processRelationChange(action.value)
            RelationFormAction.SaveRelation -> {
                if (modifyingRelation) {
                    processModifyRelation()
                } else {
                    processAddRelation()
                }
            }
        }
    }

    private fun processRelationChange(value: Long) {
        getDisplay()?.let {
            onFieldChange(it.copy(selectedRelation = it.possibleRelations.find { possibleRelations -> possibleRelations.id == value }
            ))
        }
    }

    private fun onFieldChange(newState: RelationFormDisplay) {
        viewModelScope.launch {
            _display.update { _ -> RelationFormState.Success(newState) }
        }
    }

    private fun processAddRelation() {
        viewModelScope.launch {
            getDisplay()?.selectedRelation?.id?.let {
                addRelation(objectId1 = initialArgs.objectId, objectId2 = it)
            }
        }
    }

    private fun processModifyRelation() {
        viewModelScope.launch {
            val display = getDisplay()
            if (display?.selectedRelation?.id != null && initialArgs.relatedObjectId != null) {
                modifyRelation(
                    objectId = initialArgs.objectId,
                    oldObjectId2 = initialArgs.relatedObjectId,
                    newObjectId2 = display.selectedRelation.id,
                )
            }
        }
    }

    private fun getDisplay() = (display.value as? RelationFormState.Success)?.display
}