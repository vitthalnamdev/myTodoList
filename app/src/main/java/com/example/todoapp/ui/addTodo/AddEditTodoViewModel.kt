package com.example.todoapp.ui.addTodo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.Todo
import com.example.todoapp.data.Todorepository
import com.example.todoapp.utils.UiEvent

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val repository:  Todorepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var todo by mutableStateOf<Todo?>(null)
        private set

    var title by mutableStateOf ("")
        private set

    var description by mutableStateOf("")
        private set

    private val _uiEvent = Channel <UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val todoId = savedStateHandle.get<Int>("todoId")!!
        if (todoId !=-1) {
            viewModelScope.launch {
                repository.getbyId(todoId)?.let {todo ->
                    title = todo.title
                    description = todo.description ?: ""
                    this@AddEditTodoViewModel.todo = todo
                }
            }
        }
    }

    fun onEvent (event: AddEditTodoEvent) {
        when (event) {
            is AddEditTodoEvent.OnTitleChanged ->
                title= event.title

            is AddEditTodoEvent.OnDescriptionChanged ->
                description = event.description

            is AddEditTodoEvent.OnSaveTodoClicked -> {
                viewModelScope.launch {
                    if (title.isBlank()) {
                        sendUiEvent(UiEvent.showSnackbar (
                            message = "The title can't be empty",
                            action = ""
                        ))
                        return@launch
                    }
                    repository.addTodo(
                        Todo (
                            title = title,
                            description = description,
                            isdone = todo?.isdone ?: false,
                            id = todo?.id?:-1
                        )
                    )
                    sendUiEvent(UiEvent.popBackStack)
                }
            }
            else -> TODO()
        }
    }
    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}