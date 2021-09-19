package com.mohitajwani.architectureapp.domain

import com.mohitajwani.architectureapp.data.Task
import com.mohitajwani.architectureapp.data.source.TasksRepository
import com.mohitajwani.architectureapp.util.wrapEspressoIdlingResource

class CompleteTaskUseCase(
    private val tasksRepository: TasksRepository
) {
    suspend operator fun invoke(task: Task) {

        wrapEspressoIdlingResource {
            tasksRepository.completeTask(task)
        }
    }
}