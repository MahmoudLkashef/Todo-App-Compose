package com.mahmoudreda.todolistcompose.home_flow.domain.model

data class Task(var id: Long = 0, val title: String, val priority: Int, val isCompleted: Boolean)

