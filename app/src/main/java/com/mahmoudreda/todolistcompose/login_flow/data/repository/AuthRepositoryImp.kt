package com.mahmoudreda.todolistcompose.login_flow.data.repository

import com.mahmoudreda.todolistcompose.login_flow.data.local.LoginLocalDataSource
import com.mahmoudreda.todolistcompose.login_flow.data.remote.AuthRemoteDataSource
import com.mahmoudreda.todolistcompose.login_flow.domain.model.Login
import com.mahmoudreda.todolistcompose.login_flow.domain.model.SignUp
import com.mahmoudreda.todolistcompose.login_flow.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val loginDataSource: LoginLocalDataSource,
    private val authRemoteDataSource: AuthRemoteDataSource
):
    AuthRepository {
    override suspend fun isValidLoginCredentials(loginData: Login):Boolean {
        return loginDataSource.isValidLoginCredentials(loginData)
    }

    override suspend fun signUp(signUp: SignUp): Boolean {
        return authRemoteDataSource.signUp(signUp)
    }
}