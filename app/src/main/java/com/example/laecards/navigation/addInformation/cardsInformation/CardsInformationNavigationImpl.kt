package com.example.laecards.navigation.addInformation.cardsInformation

import android.content.Context
import android.content.Intent
import com.example.laecards.navigation.addInformation.cardsInformation.CardsInformationNavigation.Companion.CARD_ID
import com.example.laecards.presentation.ui.cardsInformation.CardsInformationActivity

class CardsInformationNavigationImpl : CardsInformationNavigation {
    override fun getCards(
        context: Context,
        cardId: Int
    ): Intent {
        return Intent(context, CardsInformationActivity::class.java).apply {
            putExtra(CARD_ID, cardId)
        }
    }
}