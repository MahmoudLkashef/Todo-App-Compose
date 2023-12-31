package com.mahmoudreda.todolistcompose.home_flow.domain.usecase

import com.mahmoudreda.todolistcompose.home_flow.domain.model.Task
import com.mahmoudreda.todolistcompose.home_flow.domain.repository.TodoRepository

class UpdateTaskByIdUseCase (private val repository: TodoRepository) {
    suspend operator fun invoke(task: Task)=repository.updateTask(task)
}