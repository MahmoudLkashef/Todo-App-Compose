package com.mahmoudreda.todolistcompose.home_flow.data.local

import com.mahmoudreda.todolistcompose.home_flow.data.model.TaskDTO
import javax.inject.Inject

class TodoLocalDataSourceImp @Inject constructor(private val todoDao: TodoDao):TodoLocalDataSource {
    override suspend fun insertTask(task: TaskDTO) {
        todoDao.insertTask(task)
    }

    override suspend fun getTasks(): List<TaskDTO> {
        return todoDao.getTasks()
    }

    override suspend fun updateTaskById(task: TaskDTO) {
        todoDao.updateTaskById(task)
    }

    override suspend fun deleteTaskById(taskId: Long) {
        todoDao.deleteTaskById(taskId)
    }

    override suspend fun getTasksByTitle(searchQuery: String):List<TaskDTO> {
        return todoDao.getTasksByTitle(searchQuery)
    }

}