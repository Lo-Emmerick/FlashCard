package com.example.flashcards.presentation.ui.cardsInformation.state

import com.example.flashcards.data.model.Card

sealed interface CardsInformationState {
    data class Success(val result: Card) : CardsInformationState
    object Loading : CardsInformationState
    object Empty : CardsInformationState
    object Error : CardsInformationState
}