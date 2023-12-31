package com.mahmoudreda.todolistcompose.home_flow.domain.usecase


data class HomeUseCase(
    val getTasksUseCase: GetTasksUseCase,
    val getTaskByTitleUseCase: GetTaskByTitleUseCase,
    val deleteTaskByIdUseCase: DeleteTaskByIdUseCase,
    val updateTaskByIdUseCase: UpdateTaskByIdUseCase,
    val insertTaskUseCase: InsertTaskUseCase
)
