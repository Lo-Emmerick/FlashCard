package com.example.laecards.domain.useCase.searchCard

import com.example.laecards.data.model.Card

interface SearchCardUseCase {
    suspend operator fun invoke(): List<Card>
}