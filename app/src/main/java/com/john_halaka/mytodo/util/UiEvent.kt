package com.john_halaka.mytodo.util

sealed class UiEvent {
    object PopBackStack : UiEvent()
    data class Navigate(val route: String): UiEvent()
    data class ShowSnackBar(
        val message: String,
        val action: String? = null
    ) : UiEvent()
}