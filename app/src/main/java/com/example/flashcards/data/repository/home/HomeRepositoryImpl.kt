package com.example.flashcards.data.repository.home

import com.example.flashcards.data.local.dao.CardDao
import com.example.flashcards.data.model.Card
import com.example.flashcards.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val dao: CardDao
): HomeRepository {

    override suspend fun searchCard(): List<Card> = dao.getAllCard()
}