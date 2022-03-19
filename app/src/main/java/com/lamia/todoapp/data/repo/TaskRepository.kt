package com.lamia.todoapp.data.repo

import com.lamia.todoapp.data.source.TaskDao
import com.lamia.todoapp.model.Task
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun deleteTask(task: Task) {
        return taskDao.deleteTask(task)
    }

    suspend fun getTasks(): List<Task> =
        withContext(ioDispatcher) {
            taskDao.getTasks()
        }


    suspend fun insertTask(task: Task) {
        return taskDao.insertTask(task)
    }

    suspend fun getTaskDetail(id: Int): Task? =
        withContext(ioDispatcher) {
            taskDao.getTaskDetail(id)
        }

}