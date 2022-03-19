package com.lamia.todoapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Two data class :
 * *Tasks -> to hold the list that come from the database
 * *Task -> to hold single task
 */

data class Tasks(
    val taskList: List<Task> = listOf()
)

@Entity
data class Task(
    @PrimaryKey
    val id: Int? = null,
    val title: String = "",
    val description: String = ""
)


