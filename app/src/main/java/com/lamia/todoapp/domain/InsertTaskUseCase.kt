package com.lamia.todoapp.domain

import com.lamia.todoapp.data.repo.TaskRepository
import com.lamia.todoapp.model.Task
import javax.inject.Inject

class InsertTaskUseCase  @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: Task){
        repository.insertTask(task)
    }
}