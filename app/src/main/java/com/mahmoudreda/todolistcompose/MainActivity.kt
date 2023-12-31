package com.mahmoudreda.todolistcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.mahmoudreda.todolistcompose.home_flow.presentation.HomeScreen
import com.mahmoudreda.todolistcompose.home_flow.presentation.HomeViewModel
import com.mahmoudreda.todolistcompose.login_flow.presentation.screens.login.LoginViewModel
import com.mahmoudreda.todolistcompose.login_flow.presentation.screens.register.SignUpScreen
import com.mahmoudreda.todolistcompose.login_flow.presentation.screens.register.SignUpViewModel
import com.mahmoudreda.todolistcompose.theme.TodoListComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition{
            false
        }
        setContent {
            TodoListComposeTheme {
                val loginViewModel:LoginViewModel= hiltViewModel()
                val homeViewModel:HomeViewModel= hiltViewModel()
                val signUpViewModel:SignUpViewModel= hiltViewModel()

                //LoginScreen(loginViewModel)
                //HomeScreen(homeViewModel)
                SignUpScreen(signUpViewModel)
            }
        }
    }
}
