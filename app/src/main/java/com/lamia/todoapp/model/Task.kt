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