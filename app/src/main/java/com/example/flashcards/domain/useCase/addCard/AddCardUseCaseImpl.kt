package com.example.flashcards.domain.useCase.addCard

import com.example.flashcards.data.local.model.CardEntity
import com.example.flashcards.domain.data.CardEdit
import com.example.flashcards.domain.repository.CardRepository

class AddCardUseCaseImpl(private val repository: CardRepository) : AddCardUseCase {
    override suspend fun invoke(card: CardEdit): Boolean {

        return repository.addCard(
            CardEntity(
                id = 0,
                first_text = card.firstText,
                second_text = card.secondText
            )
        )
    }
}