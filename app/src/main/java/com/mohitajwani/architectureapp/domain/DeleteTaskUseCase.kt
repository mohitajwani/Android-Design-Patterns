package com.mohitajwani.architectureapp.domain

import com.mohitajwani.architectureapp.data.source.TasksRepository
import com.mohitajwani.architectureapp.util.wrapEspressoIdlingResource

class DeleteTaskUseCase(
    private val tasksRepository: TasksRepository
) {
    suspend operator fun invoke(taskId: String) {

        wrapEspressoIdlingResource {
            return tasksRepository.deleteTask(taskId)
        }
    }

}