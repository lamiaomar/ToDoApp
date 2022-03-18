package com.lamia.todoapp.domain

import com.lamia.todoapp.data.repo.TaskRepository
import com.lamia.todoapp.model.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {

    suspend operator fun invoke(): List<Task>{
        return repository.getTasks()
    }

}