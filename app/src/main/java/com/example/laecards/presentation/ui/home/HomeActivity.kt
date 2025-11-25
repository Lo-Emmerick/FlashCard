package com.example.laecards.presentation.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.laecards.navigation.addInformation.addInformation.AddInformationNavigationImpl
import com.example.laecards.navigation.addInformation.cardsInformation.CardsInformationNavigationImpl
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModel()
    private val navigationAdd = AddInformationNavigationImpl()
    private val navigationCards = CardsInformationNavigationImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val state by viewModel.state.observeAsState(HomeState.Loading)

            HomeScreen(
                state = state,
                onAddClick = {
                    startActivity(navigationAdd.addInformation(this))
                },
                onCardClick = { card ->
                    startActivity(navigationCards.getCards(this, card.id))
                },
                onRetry = { viewModel.searchCard() }
            )
        }

        viewModel.searchCard()
    }

    override fun onResume() {
        super.onResume()
        viewModel.searchCard()
    }
}
