package com.mahmoudreda.todolistcompose.login_flow.domain.model

data class SignUp(
    val username: String,
    val email: String,
    val phoneNumber: String,
    val password: String,
    val confirmPassword: String
)
