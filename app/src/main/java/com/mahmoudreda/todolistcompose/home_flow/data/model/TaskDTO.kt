package com.mahmoudreda.todolistcompose.home_flow.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mahmoudreda.todolistcompose.home_flow.domain.model.Task

@Entity("todo_table")
data class TaskDTO(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val title: String,
    val priority: Int,
    val isCompleted:Boolean
)


fun TaskDTO.toTask(): Task {
    return Task(
        id = id,
        title = title,
        priority = priority,
        isCompleted=isCompleted
    )
}

fun Task.toTaskDTO(): TaskDTO{
    return TaskDTO(
        id=id,
        title=title,
        priority = priority,
        isCompleted=isCompleted
    )
}


