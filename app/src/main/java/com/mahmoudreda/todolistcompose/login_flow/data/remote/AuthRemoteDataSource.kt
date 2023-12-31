package com.mahmoudreda.todolistcompose.login_flow.data.remote

import com.mahmoudreda.todolistcompose.login_flow.domain.model.SignUp

interface AuthRemoteDataSource {

    suspend fun signUp(signUp: SignUp):Boolean
}