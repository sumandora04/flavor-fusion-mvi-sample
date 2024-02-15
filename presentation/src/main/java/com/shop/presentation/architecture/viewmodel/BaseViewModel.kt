package com.shop.presentation.architecture.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * This interface represents a user interaction with the UI.
 */
interface ViewIntent

/**
 * This interface represents the state of the UI in a ViewModel.
 */
interface ViewState

/**
 * This interface represents a side effect that can be triggered by a ViewModel.
 */
interface SideEffect

abstract class BaseViewModel<VS : ViewState, VI : ViewIntent, SE : SideEffect>(
    initialState: VS
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<VS> get() = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SE>()
    val sideEffect: SharedFlow<SE> get() = _sideEffect.asSharedFlow()

    abstract fun handleIntent(intent: VI)

    fun setIntent(intent: VI) {
        handleIntent(intent)
    }

    protected suspend fun emitState(newState: VS) {
        _state.emit(newState)
    }

    protected suspend fun emitEffect(newEffect: SE) {
        _sideEffect.emit(newEffect)
    }
}