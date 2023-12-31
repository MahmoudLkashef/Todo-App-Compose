package com.mahmoudreda.todolistcompose.login_flow.domain.usecase

import com.mahmoudreda.todolistcompose.login_flow.domain.model.Login
import com.mahmoudreda.todolistcompose.login_flow.domain.repository.AuthRepository

class LoginUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(loginData: Login)=repository.isValidLoginCredentials(loginData)
}