package com.mahmoudreda.todolistcompose.login_flow.data.local

import com.mahmoudreda.todolistcompose.login_flow.domain.model.Login

interface LoginLocalDataSource {
    suspend fun isValidLoginCredentials(loginData: Login):Boolean
}