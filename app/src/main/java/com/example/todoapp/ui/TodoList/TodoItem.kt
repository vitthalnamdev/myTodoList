package com.example.todoapp.ui.TodoList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.data.Todo
import com.example.todoapp.utils.UiEvent


@Composable
fun TodoItem(
    todo: Todo,
    onEvent: (Event)->Unit
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White)
            .clickable {
                onEvent(Event.addTodo)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Checkbox(
                checked = todo.isdone,
                onCheckedChange = {
                    var value:Boolean = todo.isdone
                    if(todo.isdone==false){
                        value = true
                    }else{
                        value = false
                    }
                    onEvent(Event.IsDone(todo.copy(
                        isdone = value
                    ))) },
                modifier = Modifier.padding(end = 16.dp)
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = todo.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = todo.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Gray
                )
            }
            IconButton(
                onClick = { onEvent(Event.deleteTodo(todo))},
                modifier = Modifier
                    .size(40.dp)
                    .padding(start = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = "Delete",
                    tint = Color.Red
                )
            }
        }
    }

}

