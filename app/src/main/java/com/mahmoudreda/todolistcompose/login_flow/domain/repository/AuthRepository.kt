package com.mahmoudreda.todolistcompose.login_flow.domain.repository

import com.mahmoudreda.todolistcompose.login_flow.domain.model.Login
import com.mahmoudreda.todolistcompose.login_flow.domain.model.SignUp

interface AuthRepository {
    suspend fun isValidLoginCredentials(loginData: Login):Boolean
    suspend fun signUp(signUp: SignUp):Boolean
}