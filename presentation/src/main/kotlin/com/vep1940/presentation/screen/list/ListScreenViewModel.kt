package com.vep1940.presentation.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vep1940.domain.usecase.DeleteObject
import com.vep1940.domain.usecase.GetObjects
import com.vep1940.presentation.model.ObjectDisplay
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

    private val _allProducts = MutableStateFlow<List<ObjectDisplay>>(listOf())
    private val _display = MutableStateFlow<ListScreenState>(ListScreenState.Loading)
    val display: StateFlow<ListScreenState> = _display

    init {
        viewModelScope.launch {
            getObjects()
                .map { it.toPresentation() }
                .catch { _display.update { ListScreenState.Error } }
                .collect { screenState ->
                    _allProducts.update { screenState }
                    processSearch(ListScreenAction.Search(getDisplay()?.search ?: ""))
                }
        }
    }

    fun onAction(action: ListScreenAction) {
        when (action) {
            is ListScreenAction.Search -> processSearch(action)
            is ListScreenAction.DeleteObject -> processDelete(action)
            else -> { /* NOTHING TO DO HERE */
            }
        }
    }

    private fun processSearch(action: ListScreenAction.Search) {

        val searchedProducts = if (action.value.isNotBlank()) {
            _allProducts.value.filter {
                it.type.contains(ignoreCase = true, other = action.value) ||
                        it.name.contains(ignoreCase = true, other = action.value) ||
                        it.description.contains(ignoreCase = true, other = action.value)
            }
        } else {
            _allProducts.value
        }

        _display.update {
            ListScreenState.Success(
                ListScreenDisplay(
                    items = searchedProducts,
                    search = action.value
                )
            )
        }
    }

    private fun processDelete(action: ListScreenAction.DeleteObject) {
        viewModelScope.launch {
            deleteObject(id = action.id)
        }
    }

    private fun getDisplay() = (display.value as? ListScreenState.Success)?.display
}