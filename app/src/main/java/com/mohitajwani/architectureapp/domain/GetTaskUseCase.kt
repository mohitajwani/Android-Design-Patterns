package com.mohitajwani.architectureapp.domain

import com.mohitajwani.architectureapp.data.Result
import com.mohitajwani.architectureapp.data.Task
import com.mohitajwani.architectureapp.data.source.TasksRepository
import com.mohitajwani.architectureapp.util.wrapEspressoIdlingResource

class GetTaskUseCase(
    private val tasksRepository: TasksRepository
) {
    suspend operator fun invoke(taskId: String, forceUpdate: Boolean = false): Result<Task> {

        wrapEspressoIdlingResource {
            return tasksRepository.getTask(taskId, forceUpdate)
        }
    }

}