package com.mahmoudreda.todolistcompose.login_flow.presentation.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoudreda.todolistcompose.login_flow.domain.model.Login
import com.mahmoudreda.todolistcompose.login_flow.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    private val _emailState = MutableLiveData<Boolean>()
    val emailState: LiveData<Boolean> get() = _emailState

    private val _passwordState = MutableLiveData<Boolean>()
    val passwordState: LiveData<Boolean> get() = _passwordState

    fun login(loginData: Login) {
        viewModelScope.launch {
            // handle validation
            val isValidData = validateLoginData(loginData)
            if (isValidData) {
                //execute UseCase
                _loginSuccess.value = loginUseCase(loginData)
            }
        }
    }

    private fun validateLoginData(loginData: Login):Boolean{
        val isEmailValid=isValidEmail(loginData.email)
        _emailState.value=isEmailValid

        val isPasswordValid=isValidPassword(loginData.password)
        _passwordState.value=isPasswordValid

        return isEmailValid && isPasswordValid
    }

    private fun isValidPassword(password:String):Boolean{
        return password.length >= 8
    }

    private fun isValidEmail(email:String):Boolean{
        val emailRegex = Regex("^\\S+@\\S+\\.com$")
        return emailRegex.matches(email)
    }

}