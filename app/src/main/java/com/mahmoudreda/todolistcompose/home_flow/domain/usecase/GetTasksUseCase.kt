package com.mahmoudreda.todolistcompose.home_flow.domain.usecase


import com.mahmoudreda.todolistcompose.home_flow.domain.repository.TodoRepository

class GetTasksUseCase(private val repository: TodoRepository) {
      suspend operator fun invoke() = repository.getTasks()
}