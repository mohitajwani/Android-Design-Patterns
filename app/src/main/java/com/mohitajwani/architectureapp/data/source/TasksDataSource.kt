
package com.mohitajwani.architectureapp.data.source

import com.mohitajwani.architectureapp.data.Result
import com.mohitajwani.architectureapp.data.Task

/**
 * Main entry point for accessing tasks data.
 */
interface TasksDataSource {

    suspend fun getTasks(): Result<List<Task>>

    suspend fun getTask(taskId: String): Result<Task>

    suspend fun saveTask(task: Task)

    suspend fun completeTask(task: Task)

    suspend fun completeTask(taskId: String)

    suspend fun activateTask(task: Task)

    suspend fun activateTask(taskId: String)

    suspend fun clearCompletedTasks()

    suspend fun deleteAllTasks()

    suspend fun deleteTask(taskId: String)
}
