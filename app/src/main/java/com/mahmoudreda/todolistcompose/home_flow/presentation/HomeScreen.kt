package com.mahmoudreda.todolistcompose.home_flow.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.mahmoudreda.todolistcompose.R
import com.mahmoudreda.todolistcompose.core.TodoListTheme
import com.mahmoudreda.todolistcompose.core.components.TodoPrimaryDialog
import com.mahmoudreda.todolistcompose.core.components.TodoPrimaryTextField
import com.mahmoudreda.todolistcompose.home_flow.domain.model.Task
import com.mahmoudreda.todolistcompose.home_flow.presentation.intent.HomeScreenIntents
import com.mahmoudreda.todolistcompose.home_flow.presentation.viewstate.HomeScreenViewState

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {


    val homeState = homeViewModel.viewStates.collectAsState()

    var totalTasksSize by remember {
        mutableStateOf(0)
    }

    var completedTasksSize by remember {
        mutableStateOf(0)
    }

    homeState.value.tasks?.let { tasks ->
        totalTasksSize = tasks.size
        completedTasksSize = tasks.filter { task -> task.isCompleted }.size
    }

    Scaffold(
        topBar = {
            SearchBar(onValueChanged = { typedText ->
                homeViewModel.executeIntent(
                    HomeScreenIntents.SearchByTaskTitle(typedText)
                )
            })
        },
        floatingActionButton = {
            FloatingActionButton() {
                homeViewModel.executeIntent(
                    HomeScreenIntents.ShowAddTaskDialog(showDialog = true)
                )
            }
        },
        containerColor = Color.Black
    ) { paddingValues ->

        Column(
            Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                stringResource(R.string.progress),
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_16)),
                fontSize = 22.sp,
                color = Color.White
            )

            ProgressCard(totalTasksSize = totalTasksSize, completedTasksSize = completedTasksSize)

            Text(
                text = stringResource(R.string.today_s_task),
                fontSize = 22.sp,
                color = Color.White,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_16))
            )

            HandleHomeState(homeState = homeState, homeViewModel = homeViewModel)

        }


        if (homeState.value.showAddTaskDialog) {
            TodoPrimaryDialog(
                dialogTitle = stringResource(id = R.string.add_new_task),
                confirmButtonTitle = stringResource(id = R.string.add),
                onDismiss = {
                    homeViewModel.executeIntent(HomeScreenIntents.ShowAddTaskDialog(false))
                },
                onConfirm = { task ->
                    homeViewModel.executeIntent(HomeScreenIntents.AddTask(task))
                    homeViewModel.executeIntent(HomeScreenIntents.ShowAddTaskDialog(false))
                }
            )
        }


    }


}

@Composable
private fun HandleHomeState(
    homeState: State<HomeScreenViewState>,
    homeViewModel: HomeViewModel
) {
    when {
        homeState.value.isSuccess -> {

            var tasksList = emptyList<Task>()

            homeState.value.tasks?.let { tasks ->
                tasksList = tasks
            }

            if (tasksList.isEmpty()) {
                EmptyListScreen()
            } else {
                TasksList(
                    tasksList = tasksList,
                    homeScreenViewModel = homeViewModel,
                    homeState.value
                )
            }

        }

        homeState.value.error != null -> {
            Toast.makeText(
                LocalContext.current,
                homeState.value.error!!.message.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksList(
    tasksList: List<Task>,
    homeScreenViewModel: HomeViewModel,
    homeState: HomeScreenViewState
) {

    LazyColumn(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_350))) {
        items(items = tasksList, key = { tasksList-> tasksList.hashCode() }) { task ->

            val dismissState = rememberDismissState(
                confirmValueChange = {
                    when (it) {
                        DismissValue.DismissedToStart -> {
                            homeScreenViewModel.executeIntent(
                                HomeScreenIntents.ShowDeleteTaskDialog(
                                    showDialog = true,
                                    task = task
                                )
                            )
                        }

                        DismissValue.DismissedToEnd -> {
                            homeScreenViewModel.executeIntent(
                                HomeScreenIntents.ShowEditTaskDialog(showDialog = true, task = task)
                            )
                        }

                        else -> {}
                    }
                    false
                }
            )

            SwipeToDismiss(
                state = dismissState,
                background = {
                    DismissBackground(dismissState = dismissState)
                },
                dismissContent = {
                    TaskItem(
                        task = task,
                        onIconClick = { isTaskCompleted ->
                            val updatedTask = task.copy(isCompleted = isTaskCompleted)
                            homeScreenViewModel.executeIntent(HomeScreenIntents.EditTask(updatedTask))
                        }
                    )
                }
            )
        }
    }


    if (homeState.showDeleteTaskDialog) {
        TaskDeleteDialog(
            onDismiss = {
                homeState.task?.let { task ->
                    homeScreenViewModel.executeIntent(
                        HomeScreenIntents.ShowDeleteTaskDialog(showDialog = false, task = task)
                    )
                }
            },
            onConfirm = {
                homeState.task?.let { task ->
                    homeScreenViewModel.executeIntent(
                        HomeScreenIntents.DeleteTask(taskId = task.id)
                    )
                    homeScreenViewModel.executeIntent(
                        HomeScreenIntents.ShowDeleteTaskDialog(false, task)
                    )
                }
            }
        )
    }

    if (homeState.showEditTaskDialog) {
        homeState.task?.let { task ->
            TodoPrimaryDialog(
                task = task,
                dialogTitle = stringResource(R.string.edit_task),
                confirmButtonTitle = stringResource(R.string.confirm),
                onDismiss = {
                    homeScreenViewModel.executeIntent(
                        HomeScreenIntents.ShowEditTaskDialog(showDialog = false, task = task)
                    )
                },
                onConfirm = { modifiedTask ->
                    homeScreenViewModel.executeIntent(HomeScreenIntents.EditTask(modifiedTask))
                    homeScreenViewModel.executeIntent(
                        HomeScreenIntents.ShowEditTaskDialog(
                            showDialog = false,
                            task = modifiedTask
                        )
                    )
                }
            )
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissBackground(dismissState: DismissState) {
    val color = when (dismissState.dismissDirection) {
        DismissDirection.EndToStart -> TodoListTheme.colors.red
        DismissDirection.StartToEnd -> TodoListTheme.colors.lightGray
        null -> Color.Transparent
    }

    val iconScale by animateFloatAsState(
        targetValue = if (dismissState.targetValue == DismissValue.Default) 0.5f else 1.3f,
        label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.padding_120))
            .padding(dimensionResource(id = R.dimen.padding_16))
            .background(color, shape = CardDefaults.shape)
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = stringResource(R.string.delete_icon),
            modifier = Modifier
                .align(CenterEnd)
                .padding(end = dimensionResource(id = R.dimen.padding_16))
                .scale(iconScale)
        )
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = stringResource(R.string.delete_icon),
            modifier = Modifier
                .align(CenterStart)
                .padding(start = dimensionResource(id = R.dimen.padding_16))
                .scale(iconScale)
        )
    }
}

@Composable
fun FloatingActionButton(onClick: () -> Unit) {
    val gradientColors = listOf(TodoListTheme.colors.end, TodoListTheme.colors.start)
    IconButton(
        onClick = { onClick() },
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = gradientColors
                ),
                shape = CircleShape
            )

    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(R.string.add_icon),
            tint = Color.Black
        )
    }
}

@Composable
fun SearchBar(onValueChanged: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_16))
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.padding_16)))
            .background(TodoListTheme.colors.lightBlack)
    ) {

        var searchText by rememberSaveable {
            mutableStateOf("")
        }

        Row(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_4))) {

            Icon(
                painter = painterResource(id = R.drawable.search_icon),
                tint = Color.Unspecified,
                contentDescription = stringResource(
                    R.string.search_icon
                ),
                modifier = Modifier
                    .weight(0.1f)
                    .align(CenterVertically)
            )

            TodoPrimaryTextField(
                value = searchText,
                placeholder = stringResource(R.string.search_task_here),
                textColor = Color.White,
                placeholderTextColor = Color.LightGray.copy(0.5f),
                unfocusedPlaceholderColor = Color.White,
                containerColor = TodoListTheme.colors.lightBlack,
                focusedColor = TodoListTheme.colors.lightBlack,
                unfocusedColor = TodoListTheme.colors.lightBlack,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.9f),
                onValueChanged = { typedText ->
                    searchText = typedText
                    onValueChanged(typedText)
                }
            )
        }
    }
}

@Composable
fun ProgressCard(totalTasksSize: Int, completedTasksSize: Int) {
    val progress = if (totalTasksSize != 0) {
        (completedTasksSize * 100) / totalTasksSize
    } else 0
    Card(
        colors = CardDefaults.cardColors(
            containerColor = TodoListTheme.colors.lightBlack
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.padding_16)),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_16))
    ) {
        Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_16))) {
            Text(
                stringResource(R.string.daily_tasks),
                fontSize = 18.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_10)))
            Text(
                "$completedTasksSize/$totalTasksSize Task Completed",
                fontSize = 16.sp,
                color = TodoListTheme.colors.lightGray
            )
            Spacer(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_10)))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.you_are_almost_done_go_ahead),
                    fontSize = 14.sp,
                    color = TodoListTheme.colors.lightGray
                )

                Text(
                    "${progress}%",
                    fontSize = 18.sp,
                    color = Color.White,
                    modifier = Modifier.padding(end = dimensionResource(id = R.dimen.padding_8))
                )
            }

            Spacer(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_4)))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.padding_16))
                    .background(
                        color = TodoListTheme.colors.purple.copy(0.4f),
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_16))
                    )
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress / 100f)
                        .height(dimensionResource(id = R.dimen.padding_16))
                        .background(
                            color = TodoListTheme.colors.purple,
                            shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_16))
                        )
                )
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, onIconClick: (Boolean) -> Unit) {
    var isTaskDone by remember {
        mutableStateOf(task.isCompleted)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.padding_120))
            .padding(dimensionResource(id = R.dimen.padding_16)),
        colors = CardDefaults.cardColors(TodoListTheme.colors.lightBlack)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.padding_16))
                    .fillMaxHeight()
                    .background(getPriorityColor(task.priority))
                    .weight(0.05f)
            )
            Text(
                text = task.title,
                fontSize = 16.sp,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(0.8f)
                    .padding(dimensionResource(id = R.dimen.padding_10))
            )

            if (isTaskDone) {
                DoneIcon {
                    isTaskDone = !isTaskDone
                    onIconClick(isTaskDone)
                }
            } else {
                TodoIcon {
                    isTaskDone = !isTaskDone
                    onIconClick(isTaskDone)
                }
            }
        }
    }
}

@Composable
fun TodoIcon(onClick: () -> Unit) {
    IconButton(onClick = { onClick() }) {
        Box(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.padding_25))
                .border(
                    width = dimensionResource(id = R.dimen.padding_2),
                    color = TodoListTheme.colors.purple,
                    shape = CircleShape
                )
        )
    }
}


@Composable
fun DoneIcon(onClick: () -> Unit) {
    IconButton(onClick = { onClick() }) {
        Box(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.padding_25))
                .background(color = TodoListTheme.colors.purple, shape = CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Done,
                contentDescription = stringResource(R.string.done_icon)
            )
        }
    }
}

@Composable
fun PriorityItem(text: String, borderColor: Color, modifier: Modifier, onClick: () -> Unit) {
    IconButton(
        onClick = { onClick() },
        modifier = modifier
            .background(
                color = TodoListTheme.colors.lightBlack,
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_16))
            )
            .border(
                width = dimensionResource(id = R.dimen.padding_2),
                color = borderColor,
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_16))
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_8)),
            contentAlignment = Center
        ) {
            Text(
                text = text,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}


@Composable
fun TaskDeleteDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                stringResource(R.string.are_you_sure_you_want_to_delete_this_task),
                color = Color.White,
                fontSize = 18.sp
            )
        },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text(stringResource(R.string.delete))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(stringResource(id = R.string.cancel))
            }
        },
        containerColor = TodoListTheme.colors.lightBlack
    )
}

@Preview
@Composable
fun EmptyListScreen() {
    Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Center) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.empty_list_icon),
                contentDescription = stringResource(R.string.empty_list_icon),
                tint = Color.Unspecified
            )
            Text(
                text = stringResource(R.string.you_haven_t_added_any_tasks_yet),
                color = Color.LightGray,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_8))
            )
        }
    }
}

fun getPriorityColor(priority: Int): Color {
    return when (priority) {
        1 -> {
            TodoListTheme.colors.high
        }

        2 -> {
            TodoListTheme.colors.medium
        }

        3 -> {
            TodoListTheme.colors.low
        }

        else -> {
            TodoListTheme.colors.default
        }
    }
}