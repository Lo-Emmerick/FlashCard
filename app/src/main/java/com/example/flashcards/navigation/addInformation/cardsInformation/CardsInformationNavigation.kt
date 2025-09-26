package com.example.flashcards.navigation.addInformation.cardsInformation

import android.content.Context
import android.content.Intent

interface CardsInformationNavigation {
    fun getCards(context: Context, cardId: Int): Intent

    companion object {
        const val CARD_ID = "CARD_ID"
    }
}