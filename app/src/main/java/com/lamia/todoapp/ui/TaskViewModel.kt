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

enum class ListState { EMPTY, NOT_EMPTY }

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

    //    UI state
    private val _status = MutableLiveData<ListState>()
    val status: LiveData<ListState> = _status

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
            if (tasksDB.isEmpty()) {
                //to display emptyList photo
                _status.value = ListState.EMPTY
            } else {
                _status.value = ListState.NOT_EMPTY
                Log.e("task", "$tasksDB")
                _taskList.update { tasks ->
                    tasks.copy(
                        taskList = tasksDB
                    )
                }
            }
        }
    }

    /**
     * get single task from the list by task id
     */
    fun getTaskDetail(id: Int) {
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

    /**
     * To delete task and update the list
     * and check if the list is empty to display empty list photo
     */
    fun deleteTask(task: Task) {
        viewModelScope.launch {
            tasksUseCases.deleteTaskUseCase.invoke(task)

            if (tasksUseCases.getTasksUseCases.invoke().isEmpty()) {
                _status.value = ListState.EMPTY
            } else {
                _taskList.update {
                    it.copy(
                        taskList = tasksUseCases.getTasksUseCases.invoke()
                    )
                }
                _status.value = ListState.NOT_EMPTY
            }
        }
    }

}