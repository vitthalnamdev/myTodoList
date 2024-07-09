package com.john_halaka.mytodo.di

import android.app.Application
import androidx.room.Room
import com.john_halaka.mytodo.data.TodoDatabase
import com.john_halaka.mytodo.data.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase (app: Application) : TodoDatabase {
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            "todo_database"
        )  .build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository (db: TodoDatabase) : TodoRepository {
        return TodoRepository(db.todoDao)
    }
}