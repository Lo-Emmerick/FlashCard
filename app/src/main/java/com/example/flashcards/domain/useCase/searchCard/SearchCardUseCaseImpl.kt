package com.example.flashcards.domain.useCase.searchCard

import com.example.flashcards.data.model.Card
import com.example.flashcards.domain.repository.CardRepository

class SearchCardUseCaseImpl(private val repository: CardRepository) : SearchCardUseCase {
    override suspend fun invoke(): List<Card> {
        return repository.searchCard()
    }
}