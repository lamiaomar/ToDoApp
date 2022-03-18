package com.lamia.todoapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey
    val id: Int? = null,
    val title: String = "",
    val description: String = ""
)

data class Tasks(
    val taskList: List<Task> = listOf(),
    val xx :List<String> = listOf<String>("ff","GG","ggg")
)