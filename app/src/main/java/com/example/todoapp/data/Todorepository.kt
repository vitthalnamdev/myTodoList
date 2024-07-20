package com.example.todoapp.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

class Todorepository(private val tododao: TodoDao) {

   suspend fun addTodo(todo: Todo){
      tododao.addTodo(todo)
   }

   suspend fun deleteTodo(todo:Todo){
       tododao.deleteTodo(todo)
   }
    fun getalltodos(): Flow<List<Todo>>{
        return tododao.getAllTodos()
    }

    suspend fun getbyId(id:Int):Todo?{
        return tododao.getTodoById(id)
    }

}