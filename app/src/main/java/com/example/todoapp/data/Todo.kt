package com.example.todoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "todo_table")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String,
    val description:String,
    val isdone:Boolean
)
