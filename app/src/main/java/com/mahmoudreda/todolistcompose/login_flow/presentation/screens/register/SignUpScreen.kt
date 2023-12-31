package com.mahmoudreda.todolistcompose.login_flow.presentation.screens.register

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.mahmoudreda.todolistcompose.R
import com.mahmoudreda.todolistcompose.core.components.TodoPrimaryButton
import com.mahmoudreda.todolistcompose.core.components.TodoPrimaryTextField

@Composable
fun SignUpScreen(signUpViewModel: SignUpViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {

        var username by remember {
            mutableStateOf("")
        }
        var email by remember {
            mutableStateOf("")
        }
        var phoneNumber by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        var confirmPassword by remember {
            mutableStateOf("")
        }
        val context = LocalContext.current
        Text(
            text = stringResource(R.string.sign_up),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = dimensionResource(id = R.dimen.padding_54))
        )
        Spacer(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_25)))
        TodoPrimaryTextField(
            value = username,
            placeholder = "Enter your name",
            onValueChanged = { username = it },
            focusedColor = Color.Black,
            unfocusedColor = Color.Black,
            unfocusedPlaceholderColor = Color.Black.copy(0.7f),
            singleLine = true,
            isError = !signUpViewModel.isUserNameValid(username),
            supportingText = signUpViewModel.userNameErrorMessage,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            leadingIcon = { Icon(Icons.Default.Person, "username icon") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_16))
        )

        TodoPrimaryTextField(
            value = email,
            placeholder = "Enter your email",
            onValueChanged = { email = it },
            focusedColor = Color.Black,
            unfocusedColor = Color.Black,
            unfocusedPlaceholderColor = Color.Black.copy(0.7f),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            leadingIcon = { Icon(Icons.Default.Email, "email icon") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_16))
        )

        TodoPrimaryTextField(
            value = phoneNumber,
            placeholder = "Enter your phone number",
            onValueChanged = { phoneNumber = it },
            focusedColor = Color.Black,
            unfocusedColor = Color.Black,
            unfocusedPlaceholderColor = Color.Black.copy(0.7f),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            leadingIcon = { Icon(Icons.Default.Phone, "phone number icon") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_16))
        )

        TodoPrimaryTextField(
            value = password,
            placeholder = "Password",
            onValueChanged = { password = it },
            focusedColor = Color.Black,
            unfocusedColor = Color.Black,
            unfocusedPlaceholderColor = Color.Black.copy(0.7f),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            leadingIcon = { Icon(Icons.Default.Lock, "password icon") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_16))
        )

        TodoPrimaryTextField(
            value = confirmPassword,
            placeholder = "Confirm password",
            onValueChanged = { confirmPassword = it },
            focusedColor = Color.Black,
            unfocusedColor = Color.Black,
            unfocusedPlaceholderColor = Color.Black.copy(0.7f),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            leadingIcon = { Icon(Icons.Default.Lock, "confirm password icon") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_16))
        )

        TodoPrimaryButton(text = "Sign Up", modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_16)),
            onClick = {}
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Already have an account? ", color = Color.Black, fontSize = 16.sp)
            Text(
                text = "Login here",
                color = Color(0xffa17fe0),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.noRippleClickable {
                    Toast.makeText(context, "Navigate to login screen", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}

@Composable
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        onClick = { onClick() }
    )
}
