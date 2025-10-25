package com.example.laecards.presentation.ui.cardsInformation.state

import com.example.laecards.data.model.Card

sealed interface CardsInformationState {
    data class Success(val result: Card) : CardsInformationState
    object Loading : CardsInformationState
    object Empty : CardsInformationState
    object Error : CardsInformationState
}