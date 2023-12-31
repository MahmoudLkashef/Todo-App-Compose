package com.mahmoudreda.todolistcompose.home_flow.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mahmoudreda.todolistcompose.home_flow.data.model.TaskDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskDTO)

    @Query("SELECT * FROM todo_table")
    suspend fun getTasks(): List<TaskDTO>

    @Update
    suspend fun updateTaskById(task: TaskDTO)

    @Query("DELETE FROM todo_table WHERE id = :taskId")
    suspend fun deleteTaskById(taskId: Long)

    @Query("SELECT * FROM todo_table WHERE title LIKE '%' || :searchQuery || '%'")
    suspend fun getTasksByTitle(searchQuery: String): List<TaskDTO>

}