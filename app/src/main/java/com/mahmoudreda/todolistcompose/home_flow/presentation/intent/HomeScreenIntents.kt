package com.mahmoudreda.todolistcompose.home_flow.presentation.intent

import com.mahmoudreda.todolistcompose.core.base.Intent
import com.mahmoudreda.todolistcompose.home_flow.domain.model.Task

sealed class HomeScreenIntents:Intent{
    data class AddTask(val task: Task):HomeScreenIntents()
    data class EditTask(val task: Task):HomeScreenIntents()
    data class DeleteTask(val taskId: Long):HomeScreenIntents()
    data class SearchByTaskTitle(val searchQuery:String):HomeScreenIntents()
    data class ShowEditTaskDialog(val showDialog:Boolean,val task: Task):HomeScreenIntents()
    data class ShowAddTaskDialog(val showDialog:Boolean):HomeScreenIntents()
    data class ShowDeleteTaskDialog(val showDialog:Boolean,val task: Task):HomeScreenIntents()
    object GetTasks:HomeScreenIntents()

}
