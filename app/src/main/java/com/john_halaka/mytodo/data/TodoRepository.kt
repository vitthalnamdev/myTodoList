package com.john_halaka.mytodo.data

import kotlinx.coroutines.flow.Flow

class TodoRepository (private val todoDao: TodoDao) {

    fun getAllTodos(): Flow<List<Todo>> {
        return todoDao.getAllTodos()
    }
    suspend fun addTodo(todo: Todo) {
        todoDao.addTodo(todo)
    }
    suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodo(todo)
    }
    suspend fun getTodoById(id: Int) : Todo? {
        return todoDao.getTodoById(id)
    }

//    suspend fun updateTodo(todo: Todo) {
//        todoDao.updateTodo(todo)
//    }
}