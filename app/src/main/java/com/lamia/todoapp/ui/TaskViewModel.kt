package com.lamia.todoapp.ui

import android.util.Log
import androidx.lifecycle.*
import com.lamia.todoapp.domain.TasksUseCases
import com.lamia.todoapp.model.Task
import com.lamia.todoapp.model.Tasks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val tasksUseCases: TasksUseCases
) : ViewModel() {

    /**
     * Stream of type Tasks() to pass the tasks list
     */
    private val _taskList = MutableStateFlow(Tasks())
    val taskList: StateFlow<Tasks> = _taskList.asStateFlow()

    private val _taskDetail = MutableStateFlow(Task())
    val taskDetail: StateFlow<Task> = _taskDetail.asStateFlow()

    init {
        getTasks()
    }

    /**
     * getTask() fun is the first to be execute when the viewModel
     * is instantiated to get tasks list.
     */
    fun getTasks() {
        viewModelScope.launch {
            val tasksDB = tasksUseCases.getTasksUseCases.invoke()
            Log.e("task", "$tasksDB")
            _taskList.update { tasks ->
                tasks.copy(
                    taskList = tasksDB
                )
            }
        }
    }

    fun getTaskDetail(id: Int){
        viewModelScope.launch {
            val taskDetail = tasksUseCases.getTaskDetailUseCase.invoke(id)
            _taskDetail.update {
                it.copy(
                    title = taskDetail!!.title,
                    description = taskDetail.description
                )
            }
        }
    }

    /**
     * To add task
     */
    fun insertTask(task: Task) {
        viewModelScope.launch {
            tasksUseCases.insertTaskUseCase.invoke(task)
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch {
            tasksUseCases.deleteTaskUseCase.invoke(task)
        }
    }

}