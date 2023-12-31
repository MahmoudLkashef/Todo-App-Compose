package com.mahmoudreda.todolistcompose.login_flow.presentation.screens.register

import androidx.lifecycle.ViewModel
import com.mahmoudreda.todolistcompose.login_flow.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
):ViewModel() {

    val userNameErrorMessage="Username must start with a letter."

    fun isUserNameValid(username:String):Boolean{
        return isUsernameLengthValid(username) && isFirstCharLetter(username)
    }

    private fun isFirstCharLetter(text: String): Boolean {
        return text.first().isLetter()
    }

    private fun isUsernameLengthValid(username: String): Boolean {
        return username.length >= 3
    }
}