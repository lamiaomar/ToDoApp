package com.lamia.todoapp.di


import android.content.Context
import androidx.room.Room
import com.lamia.todoapp.data.repo.TaskRepository
import com.lamia.todoapp.data.source.TaskDataBase
import com.lamia.todoapp.domain.*
import com.lamia.todoapp.util.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // *in the parameter context or application
    /**
     * Tell hilt how to provide task database object
     */
    @Provides
    @Singleton
    fun provideTaskDatabase(@ApplicationContext context: Context) : TaskDataBase{
        return Room.databaseBuilder(
            context ,
            TaskDataBase::class.java,
            DATABASE_NAME
        ).build()
    }

    /**
     * Provide repository and take as parameter TaskDao object
     */
    @Provides
    @Singleton
    fun provideTaskRepo(database : TaskDataBase): TaskRepository{
        return TaskRepository(database.getTaskDao())
    }

    /**
     * Provide TasksUseCase which have 4 use cases
     */
    @Provides
    @Singleton
    fun provideTaskUseCase(repository: TaskRepository): TasksUseCases{
        return TasksUseCases(
            getTasksUseCases = GetTaskUseCase(repository),
            getTaskDetailUseCase = GetTaskDetailUseCase(repository),
            deleteTaskUseCase = DeleteTaskUseCase(repository),
            insertTaskUseCase = InsertTaskUseCase(repository)
        )
    }

}