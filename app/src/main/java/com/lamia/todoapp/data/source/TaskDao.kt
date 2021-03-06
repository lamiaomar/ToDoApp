package com.lamia.todoapp.data.source

import androidx.room.*
import com.lamia.todoapp.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task : Task)

    @Query("SELECT * FROM Task")
    fun getTasks() : List<Task>

    @Query("SELECT * FROM TASK WHERE id = :id")
    suspend fun getTaskDetail(id: Int): Task?

    @Delete
    suspend fun deleteTask(task: Task)


}