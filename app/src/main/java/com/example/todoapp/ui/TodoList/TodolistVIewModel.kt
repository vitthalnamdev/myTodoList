package com.example.todoapp.ui.TodoList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.Todo
import com.example.todoapp.data.Todorepository
import com.example.todoapp.utils.Routes
import com.example.todoapp.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodolistVIewModel @Inject constructor(
    private val todorepository: Todorepository
) : ViewModel(){
    val todos = todorepository.getalltodos()
    private val _uievent = Channel<UiEvent>()
    var uievent = _uievent.receiveAsFlow()
    private var deleterecently: Todo? = null
    fun onEvent(event: Event) {
        when (event) {
            is Event.IsDone -> {
                viewModelScope.launch {
                    todorepository.addTodo(event.todo)
                }
            }
            is Event.TodoClick -> {
               send(UiEvent.navigate(Routes.Add_todo + "?todoId= ${event.todo.id}"))
            }
            is Event.UndoDelete -> {
                viewModelScope.launch {
                    todorepository.addTodo(deleterecently!!)
                }
            }
            is Event.addTodo -> {
                viewModelScope.launch {
                    send(UiEvent.navigate(Routes.Add_todo))
                }
            }
            is Event.deleteTodo -> {
                viewModelScope.launch {
                    deleterecently = event.todo
                    viewModelScope.launch {
                        todorepository.deleteTodo(event.todo)
                        send(UiEvent.showSnackbar("Deleted" , "Undo"))
                    }
                }
            }
        }

    }

    fun send(uiEvent: UiEvent) {
        viewModelScope.launch {
            _uievent.send(uiEvent)
        }
    }

}