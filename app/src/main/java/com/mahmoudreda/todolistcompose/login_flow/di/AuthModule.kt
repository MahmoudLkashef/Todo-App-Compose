package com.mahmoudreda.todolistcompose.login_flow.di

import com.mahmoudreda.todolistcompose.login_flow.data.local.LoginLocalDataSource
import com.mahmoudreda.todolistcompose.login_flow.data.local.LoginLocalDataSourceImp
import com.mahmoudreda.todolistcompose.login_flow.data.remote.AuthRemoteDataSource
import com.mahmoudreda.todolistcompose.login_flow.data.remote.AuthRemoteDataSourceImp
import com.mahmoudreda.todolistcompose.login_flow.data.repository.AuthRepositoryImp
import com.mahmoudreda.todolistcompose.login_flow.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
abstract class AuthModule {

    @Binds
    abstract fun bindLoginLocalDataSource(loginLocalDataSourceImp: LoginLocalDataSourceImp): LoginLocalDataSource

    @Binds
    abstract fun bindAuthRepository(repositoryImp: AuthRepositoryImp): AuthRepository

    @Binds
    abstract fun bindAuthRemoteDataSource(authRemoteDataSourceImp: AuthRemoteDataSourceImp):AuthRemoteDataSource
}