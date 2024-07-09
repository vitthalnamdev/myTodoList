package com.john_halaka.mytodo.ui.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.john_halaka.mytodo.data.Todo
import com.john_halaka.mytodo.data.TodoRepository
import com.john_halaka.mytodo.util.Routes
import com.john_halaka.mytodo.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    val todos = repository.getAllTodos()

    private val _uiEvent = Channel <UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedTodo : Todo? = null



    fun onEvent(event: TodoListEvent){
        when (event) {
          is TodoListEvent.OnTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO + "?todoId = ${event.todo.id}"))
          }
            is TodoListEvent.OnAddTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO))
            }
            is TodoListEvent.OnUndoDeleteClick -> {
                deletedTodo?.let {todo ->
                    viewModelScope.launch {
                        repository.addTodo(todo)
                    }
                }

            }
            is TodoListEvent.OnDeleteTodoClick -> {
                viewModelScope.launch {
                    deletedTodo = event.todo
                    repository.deleteTodo(event.todo)
                    sendUiEvent(UiEvent.ShowSnackBar (
                        message = "Todo Deleted",
                        action = "Undo"
                            ))
                }

            }
            is TodoListEvent.OnCompletedChange ->{
                viewModelScope.launch {
                    repository.addTodo(
                        event.todo.copy(
                            completed = event.isCompleted
                        )
                    )
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}