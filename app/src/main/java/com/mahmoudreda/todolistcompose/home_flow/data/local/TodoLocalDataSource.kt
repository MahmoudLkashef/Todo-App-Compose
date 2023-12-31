package com.mahmoudreda.todolistcompose.home_flow.data.local

import com.mahmoudreda.todolistcompose.home_flow.data.model.TaskDTO
import kotlinx.coroutines.flow.Flow


interface TodoLocalDataSource {

    suspend fun insertTask(task: TaskDTO)

    suspend fun getTasks(): List<TaskDTO>

    suspend fun updateTaskById(task: TaskDTO)

    suspend fun deleteTaskById(taskId: Long)

    suspend fun getTasksByTitle(searchQuery: String): List<TaskDTO>
}