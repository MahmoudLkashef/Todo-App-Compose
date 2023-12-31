package com.mahmoudreda.todolistcompose.login_flow.presentation.screens.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmoudreda.todolistcompose.R
import com.mahmoudreda.todolistcompose.login_flow.domain.model.Login
import com.mahmoudreda.todolistcompose.core.components.TodoPrimaryTextField

@Composable
fun LoginScreen(loginViewModel: LoginViewModel) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    val isEmailValid by loginViewModel.emailState.observeAsState()
    val isPasswordValid by loginViewModel.passwordState.observeAsState()
    val isLoginSuccess by loginViewModel.loginSuccess.observeAsState()


    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.microsoft_todo_svgrepo_com),
            contentDescription = stringResource(
                R.string.app_logo
            ),
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterHorizontally)
                .padding(top = 50.dp)
        )
        Text(
            stringResource(R.string.welcome_back_glad_to_see_you_again),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xff1E232C),
            fontFamily= FontFamily(Font(R.font.urbanist_black)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        TodoPrimaryTextField(
            value = email.trim(),
            placeholder = stringResource(R.string.enter_your_email),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            isError = isEmailValid?.not() ?: false,
            singleLine = true
        ) {
            email = it
        }
        TodoPrimaryTextField(
            value = password,
            placeholder = stringResource(R.string.enter_your_password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            isError = isPasswordValid?.not() ?: false,
            singleLine = true,
            visualTransformation = if (!passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val painter =
                    if (passwordVisible) painterResource(id = R.drawable.visibility) else painterResource(
                        id = R.drawable.visibility_off
                    )
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {

                    Icon(painter = painter, contentDescription = description)
                }
            }

        ) {
            password = it
        }

        Text(
            stringResource(R.string.forgot_password),
            color = Color(0xff6A707C),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier= Modifier
                .align(End)
                .padding(end = 16.dp)
        )

        Button(
            shape= RoundedCornerShape(16.dp),
            modifier= Modifier
                .fillMaxWidth()
                .height(85.dp)
                .padding(16.dp), colors= ButtonDefaults.buttonColors(
                containerColor = Color(0xff1E232C),
                contentColor = Color.White
            ) ,onClick = {loginViewModel.login(Login(email, password))}) {
            Text(text = stringResource(R.string.login))
        }

        Text(
            buildAnnotatedString {
                append(stringResource(R.string.don_t_have_an_account))
                withStyle(style= SpanStyle(fontWeight = FontWeight.Bold,color=Color(0xff35C2C1))){
                    append(stringResource(R.string.register_now))
                }
            },
            modifier = Modifier.align(CenterHorizontally),
            )

        if(isLoginSuccess==true){
            Toast.makeText(LocalContext.current, "Login Successfully", Toast.LENGTH_SHORT).show()
        }
    }
}

