package com.lamia.todoapp.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lamia.todoapp.model.Task

@Database(
    entities = [Task::class],
    version = 1
)
abstract class TaskDataBase: RoomDatabase() {

    abstract fun getTaskDao(): TaskDao

}