package com.example.flashcards.presentation.ui.cardsInformation.state

interface CardsInformationDeleteState {
    object Success : CardsInformationDeleteState
    object Loading : CardsInformationDeleteState
    object Empty : CardsInformationDeleteState
    object Error : CardsInformationDeleteState
}