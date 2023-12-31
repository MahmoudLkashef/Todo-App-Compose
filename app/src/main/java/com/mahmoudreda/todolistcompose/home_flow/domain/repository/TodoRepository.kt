package com.mahmoudreda.todolistcompose.home_flow.domain.repository

import com.mahmoudreda.todolistcompose.home_flow.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    suspend fun insertTask(task: Task)

    suspend fun updateTask(task: Task)

    suspend fun deleteTaskById(taskId: Long)

    suspend fun getTasks(): List<Task>

    suspend fun getTasksByTitle(searchQuery: String): List<Task>
}