package com.vep1940.presentation.screen.detail

import androidx.lifecycle.ViewModel
import com.vep1940.presentation.screen.detail.model.DetailScreenDisplay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailScreenViewModel : ViewModel() {

    private val _display = MutableStateFlow(
        DetailScreenDisplay(0)
    )
    val display: StateFlow<DetailScreenDisplay> = _display
}