package com.mahmoudreda.todolistcompose.home_flow.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mahmoudreda.todolistcompose.home_flow.data.model.TaskDTO

@Database(entities = [TaskDTO::class], version = 1, exportSchema = false)
abstract class TodoDatabase:RoomDatabase(){
    abstract val todoDao:TodoDao
}