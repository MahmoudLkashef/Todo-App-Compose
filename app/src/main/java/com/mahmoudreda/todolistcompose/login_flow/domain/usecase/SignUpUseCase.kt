package com.mahmoudreda.todolistcompose.login_flow.domain.usecase

import com.mahmoudreda.todolistcompose.login_flow.domain.model.SignUp
import com.mahmoudreda.todolistcompose.login_flow.domain.repository.AuthRepository

class SignUpUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(signUp: SignUp)=repository.signUp(signUp)
}