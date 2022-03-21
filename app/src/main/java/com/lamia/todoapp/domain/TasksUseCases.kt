package com.lamia.todoapp.domain


// *taskUseCases contain all use cases that will deal with TaskRepository
data class TasksUseCases(
    val getTasksUseCases: GetTaskUseCase,
    val getTaskDetailUseCase: GetTaskDetailUseCase,
    val insertTaskUseCase: InsertTaskUseCase,
    val deleteTaskUseCase: DeleteTaskUseCase
)