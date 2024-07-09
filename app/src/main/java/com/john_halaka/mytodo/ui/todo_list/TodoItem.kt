package com.john_halaka.mytodo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.john_halaka.mytodo.data.Todo
import com.john_halaka.mytodo.ui.todo_list.TodoListEvent


@Composable
    fun TodoItem(
        todo: Todo,
        onEvent: (TodoListEvent) -> Unit,
        modifier: Modifier
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = todo.title,
                        fontSize= 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    IconButton(onClick = {
                        onEvent(TodoListEvent.OnDeleteTodoClick(todo))

                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete todo"
                        )
                    }


                }
                    todo.description?.let {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = it)

                    }

            }
            Checkbox(
                checked = todo.completed,
                onCheckedChange = {isChecked ->
                    onEvent(TodoListEvent.OnCompletedChange(todo, isChecked))
                })
        }
    }
