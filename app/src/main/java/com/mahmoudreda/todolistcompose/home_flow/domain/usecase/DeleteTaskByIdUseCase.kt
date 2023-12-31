package com.mahmoudreda.todolistcompose.home_flow.domain.usecase

import com.mahmoudreda.todolistcompose.home_flow.domain.repository.TodoRepository

class DeleteTaskByIdUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(taskId:Long)=repository.deleteTaskById(taskId)
}