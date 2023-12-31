package com.mahmoudreda.todolistcompose.home_flow.presentation.viewstate

import com.mahmoudreda.todolistcompose.core.base.ViewState
import com.mahmoudreda.todolistcompose.home_flow.domain.model.Task
import com.mahmoudreda.todolistcompose.home_flow.presentation.intent.HomeScreenIntents

data class HomeScreenViewState(
    val tasks: List<Task>? = null,
    val isSuccess: Boolean = false,
    val error:Throwable? = null,
    val showAddTaskDialog: Boolean=false,
    val showEditTaskDialog: Boolean=false,
    val showDeleteTaskDialog: Boolean=false,
    val task: Task? = null
): ViewState
