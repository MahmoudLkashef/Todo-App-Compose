package com.mahmoudreda.todolistcompose.core.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation

import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoPrimaryTextField(
    value:String,
    modifier: Modifier=Modifier,
    placeholder:String,
    label:String="",
    isError: Boolean = false,
    singleLine: Boolean =false,
    supportingText: String="",
    //supportingText: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    textColor:Color = Color.Black,
    placeholderTextColor:Color = Color.Black,
    unfocusedPlaceholderColor: Color=Color.White,
    containerColor:Color = Color.White,
    unfocusedContainerColor:Color = Color.White,
    focusedColor:Color = Color.White,
    unfocusedColor:Color = Color.White,
    onValueChanged:(String)->Unit
) {

    OutlinedTextField(
        value = value,
        onValueChange = { newText ->
            onValueChanged(newText)
        },
        //label = { Text(text = label)},
        isError = isError,
        singleLine=singleLine,
        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
            focusedContainerColor = containerColor,
            unfocusedContainerColor = unfocusedContainerColor,
            focusedTextColor = textColor,
            unfocusedTextColor = textColor,
            focusedPlaceholderColor = placeholderTextColor,
            unfocusedPlaceholderColor=unfocusedPlaceholderColor,
            focusedBorderColor = focusedColor,
            unfocusedBorderColor = unfocusedColor
        ),
        placeholder = { Text(placeholder) },
        supportingText ={ Text(text = if (isError)supportingText else "")},
        shape = RoundedCornerShape(16.dp),
        modifier = modifier,
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        visualTransformation=visualTransformation,
        keyboardOptions=keyboardOptions,
    )
}

