package com.example.todoapp.di

import android.app.Application
import androidx.room.Room
import com.example.todoapp.data.TodoDatabase
import com.example.todoapp.data.Todorepository
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
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(db:TodoDatabase):Todorepository{
        return Todorepository(db.todoDao)
    }

}