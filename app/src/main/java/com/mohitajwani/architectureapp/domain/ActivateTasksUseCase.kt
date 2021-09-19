package com.mohitajwani.architectureapp.domain

import com.mohitajwani.architectureapp.data.Task
import com.mohitajwani.architectureapp.data.source.TasksRepository
import com.mohitajwani.architectureapp.util.wrapEspressoIdlingResource

class ActivateTaskUseCase(
    private val tasksRepository: TasksRepository
) {
    suspend operator fun invoke(task: Task) {

        wrapEspressoIdlingResource {
            tasksRepository.activateTask(task)
        }
    }
}