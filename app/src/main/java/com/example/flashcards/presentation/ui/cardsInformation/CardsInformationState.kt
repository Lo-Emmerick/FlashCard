package com.example.flashcards.presentation.ui.cardsInformation

import com.example.flashcards.data.model.Card

sealed interface CardsInformationState {
    data class Success(val result: List<Card>) : CardsInformationState
    object Loading : CardsInformationState
    object Error : CardsInformationState
}