package com.mahmoudreda.todolistcompose.home_flow.di

import com.mahmoudreda.todolistcompose.home_flow.domain.repository.TodoRepository
import com.mahmoudreda.todolistcompose.home_flow.domain.usecase.DeleteTaskByIdUseCase
import com.mahmoudreda.todolistcompose.home_flow.domain.usecase.GetTaskByTitleUseCase
import com.mahmoudreda.todolistcompose.home_flow.domain.usecase.GetTasksUseCase
import com.mahmoudreda.todolistcompose.home_flow.domain.usecase.HomeUseCase
import com.mahmoudreda.todolistcompose.home_flow.domain.usecase.InsertTaskUseCase
import com.mahmoudreda.todolistcompose.home_flow.domain.usecase.UpdateTaskByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideDeleteTaskByIdUseCase(repository: TodoRepository): DeleteTaskByIdUseCase {
        return DeleteTaskByIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetTaskByTitleUseCase(repository: TodoRepository): GetTaskByTitleUseCase {
        return GetTaskByTitleUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetTasksUseCase(repository: TodoRepository): GetTasksUseCase {
        return GetTasksUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateTaskByIdUseCase(repository: TodoRepository): UpdateTaskByIdUseCase {
        return UpdateTaskByIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideInsertTaskUseCase(repository: TodoRepository):InsertTaskUseCase{
        return InsertTaskUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideHomeUseCase(repository: TodoRepository): HomeUseCase {
        return HomeUseCase(
            getTasksUseCase = GetTasksUseCase(repository),
            getTaskByTitleUseCase = GetTaskByTitleUseCase(repository),
            deleteTaskByIdUseCase = DeleteTaskByIdUseCase(repository),
            updateTaskByIdUseCase = UpdateTaskByIdUseCase(repository),
            insertTaskUseCase = InsertTaskUseCase(repository)
        )
    }
}