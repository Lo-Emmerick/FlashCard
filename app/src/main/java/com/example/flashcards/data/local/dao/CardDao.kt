package com.example.flashcards.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.flashcards.data.model.Card

@Dao
interface CardDao {
    @Query("SELECT * FROM card")
    suspend fun getAllCard(): List<Card>
}