package com.mahmoudreda.todolistcompose.home_flow.di

import android.content.Context
import androidx.room.Room
import com.mahmoudreda.todolistcompose.home_flow.data.local.TodoDao
import com.mahmoudreda.todolistcompose.home_flow.data.local.TodoDatabase
import com.mahmoudreda.todolistcompose.home_flow.data.local.TodoLocalDataSource
import com.mahmoudreda.todolistcompose.home_flow.data.local.TodoLocalDataSourceImp
import com.mahmoudreda.todolistcompose.home_flow.data.repository.TodoRepositoryImp
import com.mahmoudreda.todolistcompose.home_flow.domain.repository.TodoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class LocalBind{

    @Binds
    abstract fun bindLocalDataSource(localDataSourceImp: TodoLocalDataSourceImp):TodoLocalDataSource

    @Binds
    abstract fun bindRepository(todoRepositoryImp: TodoRepositoryImp):TodoRepository

}

@InstallIn(SingletonComponent::class)
@Module
object LocalModule {

    @Provides
    @Singleton
    fun provideDatabaseInstance(@ApplicationContext context:Context):TodoDatabase{
        return Room.databaseBuilder(
            context,
            TodoDatabase::class.java,
            "Todo Database"
        ).fallbackToDestructiveMigration().build()
    }


    @Provides
    @Singleton
    fun provideTodoDao(todoDatabase: TodoDatabase):TodoDao{
        return todoDatabase.todoDao
    }
}