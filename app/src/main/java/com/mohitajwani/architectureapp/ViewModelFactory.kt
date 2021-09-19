
package com.mohitajwani.architectureapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohitajwani.architectureapp.addedittask.AddEditTaskViewModel
import com.mohitajwani.architectureapp.data.source.TasksRepository
import com.mohitajwani.architectureapp.domain.ActivateTaskUseCase
import com.mohitajwani.architectureapp.domain.ClearCompletedTasksUseCase
import com.mohitajwani.architectureapp.domain.CompleteTaskUseCase
import com.mohitajwani.architectureapp.domain.DeleteTaskUseCase
import com.mohitajwani.architectureapp.domain.GetTaskUseCase
import com.mohitajwani.architectureapp.domain.GetTasksUseCase
import com.mohitajwani.architectureapp.domain.SaveTaskUseCase
import com.mohitajwani.architectureapp.statistics.StatisticsViewModel
import com.mohitajwani.architectureapp.taskdetail.TaskDetailViewModel
import com.mohitajwani.architectureapp.tasks.TasksViewModel

/**
 * Factory for all ViewModels.
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val tasksRepository: TasksRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(StatisticsViewModel::class.java) ->
                        StatisticsViewModel(
                            GetTasksUseCase(tasksRepository)
                        )
                    isAssignableFrom(TaskDetailViewModel::class.java) ->
                        TaskDetailViewModel(
                            GetTaskUseCase(tasksRepository),
                            DeleteTaskUseCase(tasksRepository),
                            CompleteTaskUseCase(tasksRepository),
                            ActivateTaskUseCase(tasksRepository)
                        )
                    isAssignableFrom(AddEditTaskViewModel::class.java) ->
                        AddEditTaskViewModel(
                            GetTaskUseCase(tasksRepository),
                            SaveTaskUseCase(tasksRepository)
                        )
                    isAssignableFrom(TasksViewModel::class.java) ->
                        TasksViewModel(
                            GetTasksUseCase(tasksRepository),
                            ClearCompletedTasksUseCase(tasksRepository),
                            CompleteTaskUseCase(tasksRepository),
                            ActivateTaskUseCase(tasksRepository)
                        )
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T
}
