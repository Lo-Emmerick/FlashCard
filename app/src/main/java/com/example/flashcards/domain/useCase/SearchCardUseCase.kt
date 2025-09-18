package com.example.flashcards.domain.useCase

import com.example.flashcards.data.model.Card

interface SearchCardUseCase {
    suspend operator fun invoke(): List<Card>
}