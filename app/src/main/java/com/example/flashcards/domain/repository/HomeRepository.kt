package com.example.flashcards.domain.repository

import com.example.flashcards.data.model.Card

interface HomeRepository {
    suspend fun searchCard() : List<Card>
}