package com.example.todoapp.ui.addTodo

sealed class AddEditTodoEvent {
    data class OnTitleChanged(val title: String) : AddEditTodoEvent()
    data class OnDescriptionChanged(val description: String) : AddEditTodoEvent()
    object OnSaveTodoClicked : AddEditTodoEvent()

}
