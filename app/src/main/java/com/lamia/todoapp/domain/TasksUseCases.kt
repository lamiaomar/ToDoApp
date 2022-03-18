package com.lamia.todoapp.domain

data class TasksUseCases(
    val getTasksUseCases: GetTaskUseCase,
    val insertTaskUseCase: InsertTaskUseCase,
    val deleteTaskUseCase: DeleteTaskUseCase
)