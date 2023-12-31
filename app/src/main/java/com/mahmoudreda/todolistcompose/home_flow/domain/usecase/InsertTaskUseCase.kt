package com.mahmoudreda.todolistcompose.home_flow.domain.usecase

import com.mahmoudreda.todolistcompose.home_flow.domain.model.Task
import com.mahmoudreda.todolistcompose.home_flow.domain.repository.TodoRepository

class InsertTaskUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(task:Task) = repository.insertTask(task)
}