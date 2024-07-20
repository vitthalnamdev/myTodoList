package com.example.todoapp.ui.TodoList

import com.example.todoapp.data.Todo

sealed class Event {
    class deleteTodo(val todo: Todo):Event()
    class TodoClick(val todo:Todo):Event()
    class IsDone(val todo: Todo):Event()
    object UndoDelete:Event()
    object addTodo:Event()
}