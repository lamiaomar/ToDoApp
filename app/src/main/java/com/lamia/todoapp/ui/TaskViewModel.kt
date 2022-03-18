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

    private val _taskList = MutableStateFlow(Tasks())
    val taskList: StateFlow<Tasks> = _taskList.asStateFlow()

    var _allTask =  MutableLiveData<List<Task>>()
    val allTask : LiveData<List<Task>> = _allTask

    init {
        getTasks()
        viewModelScope.launch {
//            _allTask = tasksUseCases.getTasksUseCases.invoke().asLiveData()
        }
    }

    fun getTasks() {
        viewModelScope.launch {
            val x = tasksUseCases.getTasksUseCases.invoke()
            Log.e("Hh","$x")

            _taskList.update { tasks ->
                tasks.copy(
                    taskList = x
                )
            }
            Log.e("Hh","${_taskList.value}")
        }
    }

    fun insertTask(task: Task) {
        viewModelScope.launch {
            tasksUseCases.insertTaskUseCase.invoke(task)
        }
    }

}