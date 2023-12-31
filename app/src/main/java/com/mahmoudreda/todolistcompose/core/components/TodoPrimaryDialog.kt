package com.mahmoudreda.todolistcompose.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.mahmoudreda.todolistcompose.R
import com.mahmoudreda.todolistcompose.core.TodoListTheme
import com.mahmoudreda.todolistcompose.home_flow.domain.model.Task
import com.mahmoudreda.todolistcompose.home_flow.presentation.PriorityItem

@Composable
fun TodoPrimaryDialog(
    task: Task? = null,
    dialogTitle: String,
    confirmButtonTitle: String,
    onDismiss: () -> Unit,
    onConfirm: (Task) -> Unit
) {
    var taskTitle by remember {
        mutableStateOf(task?.title ?: "")
    }

    var taskPriority by remember {
        mutableStateOf(0)
    }

    val getPriorityNumber = mapOf(
        "High" to 1,
        "Medium" to 2,
        "Low" to 3
    )

    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(dimensionResource(id = R.dimen.padding_16)),
            colors = CardDefaults.cardColors(containerColor = TodoListTheme.colors.lightBlack)
        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
            ) {
                Text(
                    text = dialogTitle,
                    fontSize = 22.sp,
                    color = Color.White,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_16))
                )
                TodoPrimaryTextField(
                    value = taskTitle,
                    textColor = Color.White,
                    placeholder = stringResource(R.string.task_title),
                    onValueChanged = { typedTitle ->
                        taskTitle = typedTitle
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    placeholderTextColor = Color.LightGray.copy(0.5f),
                    unfocusedPlaceholderColor = Color.White,
                    containerColor = TodoListTheme.colors.lightBlack,
                    focusedColor = TodoListTheme.colors.lightGray,
                    unfocusedColor = TodoListTheme.colors.lightGray,
                    singleLine = true
                )

                Text(
                    text = stringResource(R.string.priority),
                    fontSize = 20.sp,
                    color = TodoListTheme.colors.lightGray,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_16))
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val priorityList = mapOf(
                        "High" to TodoListTheme.colors.high,
                        "Medium" to TodoListTheme.colors.medium,
                        "Low" to TodoListTheme.colors.low
                    )
                    priorityList.forEach { priority ->
                        PriorityItem(
                            text = priority.key,
                            borderColor = priority.value,
                            modifier = Modifier
                                .padding(dimensionResource(id = R.dimen.padding_8))
                                .weight(1f / priorityList.size.toFloat()),
                            onClick = { taskPriority = getPriorityNumber[priority.key]!! }
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_8))
                ) {
                    TodoPrimaryButton(
                        text = stringResource(R.string.cancel),
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = dimensionResource(id = R.dimen.padding_8)),
                        colors = ButtonDefaults.buttonColors(TodoListTheme.colors.secondaryGray),
                        onClick = {
                            onDismiss()
                        }
                    )

                    TodoPrimaryButton(
                        text = confirmButtonTitle,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = dimensionResource(id = R.dimen.padding_8)),
                        colors = ButtonDefaults.buttonColors(TodoListTheme.colors.secondaryPurple),
                        onClick = {
                            onConfirm(
                                Task(
                                    id = task?.id ?: 0,
                                    title = taskTitle,
                                    priority = taskPriority,
                                    isCompleted = task?.isCompleted ?: false
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}