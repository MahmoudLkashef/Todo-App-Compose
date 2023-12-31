package com.mahmoudreda.todolistcompose.core.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.dimensionResource
import com.mahmoudreda.todolistcompose.R

@Composable
fun TodoPrimaryButton(
    text: String,
    modifier: Modifier=Modifier,
    textColor: Color = Color.Unspecified,
    colors: ButtonColors =ButtonDefaults.buttonColors(),
    shape: Shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_16)),
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = shape,
        colors = colors,
        onClick = { onClick() }
    ) {
        Text(text = text, color = textColor)
    }
}