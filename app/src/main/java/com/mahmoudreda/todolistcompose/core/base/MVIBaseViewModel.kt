package com.mahmoudreda.todolistcompose.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

abstract class MVIBaseViewModel<I : Intent, VS : ViewState, R : Result<VS>> : ViewModel() {

    abstract val defaultViewState: VS

    private val intentsChannel = Channel<I>(Channel.UNLIMITED)

    private val _viewStates = MutableStateFlow<VS>(defaultViewState)
    val viewStates: StateFlow<VS> = _viewStates
    val job = Job()

    val context = job + Dispatchers.Main

    init {
        observeOnIntentsChannel()
    }


    private fun observeOnIntentsChannel() {
        viewModelScope.launch(context) {
            supervisorScope {
                intentsChannel.consumeEach { action ->
                    launch {
                        val results: Flow<R> = handleAction(action)
                        results.collect { result: R ->
                            _viewStates.value = reduce(result)
                        }
                    }
                }
            }
        }
    }

    fun executeIntent(intent: I) {
        viewModelScope.launch {
            intentsChannel.send(intent)
        }
    }

    @Synchronized
    fun emitState(
        stateReducer: (oldState: VS) -> VS
    ) {
        val newState = stateReducer(_viewStates.value)
        if (_viewStates.value != newState) {
            _viewStates.update {
                newState
            }
        }
    }

    abstract fun handleAction(intent: I): Flow<R>


    //covert result to view state
    private fun reduce(result: R): VS {
        return result.reduce(defaultState = defaultViewState, oldState = viewStates.value)
    }
}