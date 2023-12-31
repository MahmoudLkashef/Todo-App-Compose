package com.mahmoudreda.todolistcompose.home_flow.domain.usecase

import com.mahmoudreda.todolistcompose.home_flow.domain.repository.TodoRepository

class GetTaskByTitleUseCase(private val repository: TodoRepository) {
     suspend operator fun invoke(searchQuery:String)=repository.getTasksByTitle(searchQuery)
}