package com.example.flashcards.domain.useCase.searchCard

import com.example.flashcards.data.model.Card

interface SearchCardUseCase {
    suspend operator fun invoke(): List<Card>
}