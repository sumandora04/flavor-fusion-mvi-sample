package com.shop.presentation.architecture.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * This interface represents a user interaction with the UI.
 */
interface ViewIntent

/**
 * This interface represents the state of the UI in a ViewModel.
 */
interface ViewState


abstract class BaseViewModel<VS : ViewState,
        VI : ViewIntent>(initialState: VS) : ViewModel() {
    private val _state = MutableStateFlow<ViewState>(initialState)
    val state: StateFlow<ViewState> get() = _state


    abstract fun handleIntent(intent: ViewIntent)

    fun setIntent(intent: ViewIntent) {
        viewModelScope.launch {
            handleIntent(intent)
        }
    }

    fun setState(state: ViewState) {
        _state.value = state
    }
}