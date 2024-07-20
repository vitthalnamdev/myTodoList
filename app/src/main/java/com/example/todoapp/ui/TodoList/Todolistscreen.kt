package com.example.todoapp.ui.TodoList

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.data.Todo
import com.example.todoapp.utils.UiEvent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TodolistScreen(
    viewModel: TodolistVIewModel = hiltViewModel<TodolistVIewModel>(),
    onNavigate : (UiEvent.navigate)->Unit
){
    val todos  = viewModel.todos.collectAsState(emptyList<Todo>())
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true){
       viewModel.uievent.collect{event->
           when(event){
               is UiEvent.navigate -> {
                   onNavigate(event)
               }
               is UiEvent.showSnackbar -> {
                   val result =  scaffoldState.snackbarHostState.showSnackbar(
                       message = event.message,
                       actionLabel = event.action
                   )
                   if (result == SnackbarResult.ActionPerformed) {
                       viewModel.onEvent(Event.UndoDelete)
                   }
               }
               else -> TODO()
           }
       }
    }
    Scaffold (
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton (onClick = {
                viewModel.onEvent(Event.addTodo)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add todo")
            }

        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
             items(todos.value){ todo->
                 println("The title is ${todo.title}")
                 TodoItem(todo,
                        onEvent = viewModel::onEvent
                     )
             }
        }
    }
}