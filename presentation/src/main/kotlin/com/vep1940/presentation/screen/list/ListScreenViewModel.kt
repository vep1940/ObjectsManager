package com.vep1940.presentation.screen.list

import androidx.lifecycle.ViewModel
import com.vep1940.presentation.screen.list.model.ListScreenDisplay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ListScreenViewModel : ViewModel() {

    private val _display = MutableStateFlow(
        ListScreenDisplay(whatever = 0)
    )
    val display: StateFlow<ListScreenDisplay> = _display
}