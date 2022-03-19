package com.lamia.todoapp.domain

import com.lamia.todoapp.data.repo.TaskRepository
import com.lamia.todoapp.model.Task
import javax.inject.Inject

class GetTaskDetailUseCase @Inject constructor(
    private val repository: TaskRepository
) {

    suspend operator fun invoke(id: Int): Task?{
        return repository.getTaskDetail(id)
    }

}
