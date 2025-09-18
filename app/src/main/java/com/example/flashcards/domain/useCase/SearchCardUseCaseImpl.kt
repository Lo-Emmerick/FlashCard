package com.example.flashcards.domain.useCase

import com.example.flashcards.data.model.Card
import com.example.flashcards.domain.repository.HomeRepository

class SearchCardUseCaseImpl(private val repository: HomeRepository) {
    suspend operator fun invoke() : List<Card>{
        return repository.searchCard()
    }
}