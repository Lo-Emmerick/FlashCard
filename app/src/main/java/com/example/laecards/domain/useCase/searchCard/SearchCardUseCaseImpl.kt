package com.example.laecards.domain.useCase.searchCard

import com.example.laecards.data.model.Card
import com.example.laecards.domain.repository.CardRepository

class SearchCardUseCaseImpl(private val repository: CardRepository) : SearchCardUseCase {
    override suspend fun invoke(): List<Card> {
        return repository.searchCard()
    }
}