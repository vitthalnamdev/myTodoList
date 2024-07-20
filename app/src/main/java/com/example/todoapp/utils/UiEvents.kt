package com.example.todoapp.utils

sealed class UiEvent {
    object popBackStack : UiEvent()
    class navigate(val route:String):UiEvent()
    class showSnackbar(
        val message:String,
        val action:String
    ):UiEvent()
}