package com.example.todoapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo_table")
    fun getAllTodos(): Flow<List<Todo>>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("SELECT * FROM todo_table WHERE id =:id")
    suspend fun getTodoById(id: Int): Todo?

//    @Update
//    fun updateTodo(todo: Todo)
}