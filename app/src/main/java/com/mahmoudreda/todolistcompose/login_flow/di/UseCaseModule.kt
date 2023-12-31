package com.mahmoudreda.todolistcompose.login_flow.di

import com.mahmoudreda.todolistcompose.login_flow.domain.repository.AuthRepository
import com.mahmoudreda.todolistcompose.login_flow.domain.usecase.LoginUseCase
import com.mahmoudreda.todolistcompose.login_flow.domain.usecase.SignUpUseCase
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
    fun provideLoginUseCase(repository: AuthRepository): LoginUseCase {
        return LoginUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSignUpUseCase(repository: AuthRepository): SignUpUseCase {
        return SignUpUseCase(repository)
    }
}