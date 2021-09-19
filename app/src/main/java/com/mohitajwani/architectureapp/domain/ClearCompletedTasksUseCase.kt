package com.mohitajwani.architectureapp.domain

import com.mohitajwani.architectureapp.data.source.TasksRepository
import com.mohitajwani.architectureapp.util.wrapEspressoIdlingResource

class ClearCompletedTasksUseCase(
    private val tasksRepository: TasksRepository
) {
    suspend operator fun invoke() {

        wrapEspressoIdlingResource {
            tasksRepository.clearCompletedTasks()
        }
    }
}