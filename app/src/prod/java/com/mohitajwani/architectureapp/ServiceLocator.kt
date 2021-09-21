package com.mohitajwani.architectureapp

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import com.mohitajwani.architectureapp.data.source.DefaultTasksRepository
import com.mohitajwani.architectureapp.data.source.TasksDataSource
import com.mohitajwani.architectureapp.data.source.TasksRepository
import com.mohitajwani.architectureapp.data.source.local.TasksLocalDataSource
import com.mohitajwani.architectureapp.data.source.local.ToDoDatabase
import com.mohitajwani.architectureapp.data.source.remote.TasksRemoteDataSource
import kotlinx.coroutines.runBlocking

/**
 * A Service Locator for the [TasksRepository]. This is the prod version, with a
 * the "real" [TasksRemoteDataSource].
 */
object ServiceLocator {

    private val lock = Any()
    private var database: ToDoDatabase? = null
    @Volatile
    var tasksRepository: TasksRepository? = null
        @VisibleForTesting set

    fun provideTasksRepository(context: Context): TasksRepository {
        synchronized(this) {
            return tasksRepository ?: tasksRepository ?: createTasksRepository(context)
        }
    }

    private fun createTasksRepository(context: Context): TasksRepository {
        return DefaultTasksRepository(TasksRemoteDataSource, createTaskLocalDataSource(context))
    }

    private fun createTaskLocalDataSource(context: Context): TasksDataSource {
        val database = database ?: createDataBase(context)
        return TasksLocalDataSource(database.taskDao())
    }

    private fun createDataBase(context: Context): ToDoDatabase {
        val result = Room.databaseBuilder(
            context.applicationContext,
            ToDoDatabase::class.java, "Tasks.db"
        ).build()
        database = result
        return result
    }

    @VisibleForTesting
    fun resetRepository() {
        synchronized(lock) {
            runBlocking {
                TasksRemoteDataSource.deleteAllTasks()
            }
            // Clear all data to avoid test pollution.
            database?.apply {
                clearAllTables()
                close()
            }
            database = null
            tasksRepository = null
        }
    }
}
