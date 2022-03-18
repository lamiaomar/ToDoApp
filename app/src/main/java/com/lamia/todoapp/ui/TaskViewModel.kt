package com.lamia.todoapp.ui

import androidx.lifecycle.*
import com.lamia.todoapp.domain.TasksUseCases
import com.lamia.todoapp.model.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val tasksUseCases: TasksUseCases
) : ViewModel() {

    private val _taskList = MutableStateFlow(Task())
    val taskList: StateFlow<Task> = _taskList.asStateFlow()

    var _allTask =  MutableLiveData<List<Task>>()
    val allTask : LiveData<List<Task>> = _allTask



    val q = MutableLiveData<List<Task>>()



    init {
        getTasks()
        viewModelScope.launch {
//            _allTask = tasksUseCases.getTasksUseCases.invoke().asLiveData()
        }
    }

    fun getTasks() {
        viewModelScope.launch {
            val x = tasksUseCases.getTasksUseCases.invoke()

            _taskList.update {
                it.copy(
                    title = it.title,
                    description = it.description
                )
            }

//            _taskList.update { task ->
//                task.copy(
//                    x.
//                    map {
//                        Task(
//                            title = it.title,
//                            description = it[0].description
//                        )
//                    }
//                )
//            }
        }
    }

    fun insertTask(task: Task) {
        viewModelScope.launch {
            tasksUseCases.insertTaskUseCase.invoke(task)
        }
    }

}