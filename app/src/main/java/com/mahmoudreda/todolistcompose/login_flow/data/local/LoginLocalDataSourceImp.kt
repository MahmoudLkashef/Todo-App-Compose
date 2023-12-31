package com.mahmoudreda.todolistcompose.login_flow.data.local

import com.mahmoudreda.todolistcompose.login_flow.domain.model.Login
import javax.inject.Inject

class LoginLocalDataSourceImp @Inject constructor(): LoginLocalDataSource {
    override suspend fun isValidLoginCredentials(loginData: Login):Boolean {
        return true
    }
}