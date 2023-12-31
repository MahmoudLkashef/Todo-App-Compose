package com.mahmoudreda.todolistcompose.home_flow.data.repository

import com.mahmoudreda.todolistcompose.home_flow.data.local.TodoLocalDataSource
import com.mahmoudreda.todolistcompose.home_flow.data.model.TaskDTO
import com.mahmoudreda.todolistcompose.home_flow.data.model.toTask
import com.mahmoudreda.todolistcompose.home_flow.data.model.toTaskDTO
import com.mahmoudreda.todolistcompose.home_flow.domain.model.Task
import com.mahmoudreda.todolistcompose.home_flow.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodoRepositoryImp @Inject constructor(
    private val localDataSource: TodoLocalDataSource
) : TodoRepository {

    override suspend fun insertTask(task: Task) {
        localDataSource.insertTask(task.toTaskDTO())
    }

    override suspend fun updateTask(task: Task) {
        localDataSource.updateTaskById(task.toTaskDTO())
    }

    override suspend fun deleteTaskById(taskId: Long) {
        localDataSource.deleteTaskById(taskId)
    }

    override suspend fun getTasks(): List<Task> {
        return localDataSource.getTasks().map { taskDTO -> taskDTO.toTask() }
    }

    override suspend fun getTasksByTitle(searchQuery: String): List<Task> {
        return localDataSource.getTasksByTitle(searchQuery).map { taskDTO -> taskDTO.toTask() }
    }

}