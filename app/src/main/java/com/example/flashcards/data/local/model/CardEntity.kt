package com.example.flashcards.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "card",
    indices = [Index(value = ["first_text"], unique = true)]
)

class CardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "first_text")
    val first_text: String,

    @ColumnInfo(name = "second_text")
    val second_text: String,
)