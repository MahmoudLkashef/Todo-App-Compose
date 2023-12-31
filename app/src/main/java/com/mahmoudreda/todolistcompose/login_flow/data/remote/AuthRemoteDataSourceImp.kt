package com.mahmoudreda.todolistcompose.login_flow.data.remote

import com.mahmoudreda.todolistcompose.login_flow.domain.model.SignUp
import javax.inject.Inject

class AuthRemoteDataSourceImp @Inject constructor(

):AuthRemoteDataSource {
    override suspend fun signUp(signUp: SignUp): Boolean {
        return true
    }

}