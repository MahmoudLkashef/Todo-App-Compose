package com.mahmoudreda.todolistcompose.home_flow.presentation

import android.util.Log
import com.mahmoudreda.todolistcompose.home_flow.presentation.result.HomeScreenResult
import androidx.lifecycle.viewModelScope
import com.mahmoudreda.todolistcompose.core.base.MVIBaseViewModel
import com.mahmoudreda.todolistcompose.home_flow.domain.model.Task
import com.mahmoudreda.todolistcompose.home_flow.domain.usecase.HomeUseCase
import com.mahmoudreda.todolistcompose.home_flow.presentation.intent.HomeScreenIntents
import com.mahmoudreda.todolistcompose.home_flow.presentation.viewstate.HomeScreenViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeUseCase: HomeUseCase) :
    MVIBaseViewModel<HomeScreenIntents, HomeScreenViewState, HomeScreenResult>() {

    override val defaultViewState: HomeScreenViewState
        get() = HomeScreenViewState()

    init {
        executeIntent(HomeScreenIntents.GetTasks)
    }

    override fun handleAction(intent: HomeScreenIntents): Flow<HomeScreenResult> {
        return flow {
            when (intent) {
                is HomeScreenIntents.AddTask -> {
                    handleAddTaskIntent(this, intent.task)
                }

                is HomeScreenIntents.DeleteTask -> {
                    handleDeleteTaskIntent(this, intent.taskId)
                }

                is HomeScreenIntents.EditTask -> {
                    handleEditTaskIntent(this, intent.task)
                }

                is HomeScreenIntents.GetTasks -> {
                    handleGetTasksIntent(this)
                }

                is HomeScreenIntents.SearchByTaskTitle -> {
                    handleSearchByTaskTitleIntent(this, intent.searchQuery)
                }

                is HomeScreenIntents.ShowAddTaskDialog -> {
                    emitState { homeScreenViewState ->
                        homeScreenViewState.copy(showAddTaskDialog = intent.showDialog)
                    }
                }

                is HomeScreenIntents.ShowDeleteTaskDialog -> {
                    emitState { homeScreenViewState ->
                        homeScreenViewState.copy(
                            showDeleteTaskDialog = intent.showDialog,
                            task = intent.task
                        )
                    }
                }

                is HomeScreenIntents.ShowEditTaskDialog -> {
                    emitState { homeScreenViewState ->
                        homeScreenViewState.copy(
                            showEditTaskDialog = intent.showDialog,
                            task = intent.task
                        )
                    }
                }
            }
        }
    }


    private fun handleAddTaskIntent(
        flowCollector: FlowCollector<HomeScreenResult>,
        task: Task
    ) = viewModelScope.launch(context) {
        homeUseCase.insertTaskUseCase(task)
        flowCollector.emit(
            HomeScreenResult.GetPosts(
                HomeScreenViewState(tasks = homeUseCase.getTasksUseCase(), isSuccess = true)
            )
        )
    }

    private suspend fun handleGetTasksIntent(flowCollector: FlowCollector<HomeScreenResult>) {
        flowCollector.emit(
            HomeScreenResult.GetPosts(
                HomeScreenViewState(tasks = homeUseCase.getTasksUseCase(), isSuccess = true)
            )
        )
    }

    private suspend fun handleEditTaskIntent(
        flowCollector: FlowCollector<HomeScreenResult>,
        task: Task
    ) {
        homeUseCase.updateTaskByIdUseCase(task)
        flowCollector.emit(
            HomeScreenResult.GetPosts(
                HomeScreenViewState(tasks = homeUseCase.getTasksUseCase(), isSuccess = true)
            )
        )
    }

    private suspend fun handleDeleteTaskIntent(
        flowCollector: FlowCollector<HomeScreenResult>,
        taskId: Long
    ) {
        homeUseCase.deleteTaskByIdUseCase(taskId)
        flowCollector.emit(
            HomeScreenResult.GetPosts(
                HomeScreenViewState(tasks = homeUseCase.getTasksUseCase(), isSuccess = true)
            )
        )
    }


    private suspend fun handleSearchByTaskTitleIntent(
        flowCollector: FlowCollector<HomeScreenResult>,
        searchQuery: String
    ) {
        flowCollector.emit(
            HomeScreenResult.GetPosts(
                HomeScreenViewState(
                    tasks = homeUseCase.getTaskByTitleUseCase(searchQuery),
                    isSuccess = true
                )
            )
        )
    }
}


