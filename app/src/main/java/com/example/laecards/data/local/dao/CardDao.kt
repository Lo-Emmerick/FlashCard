package com.example.laecards.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.laecards.data.local.model.CardEntity
import com.example.laecards.data.model.Card

@Dao
interface CardDao {
    @Query("SELECT * FROM card")
    suspend fun getAllCard(): List<Card>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCard(card: CardEntity)

    @Query("SELECT * FROM card WHERE id >= :cardId ORDER BY id ASC")
    suspend fun getCardsAfterId(cardId: Int): List<Card>

    @Query("DELETE FROM card WHERE id = :cardId")
    suspend fun deleteCardId(cardId: Int): Int
}