package com.example.flashcards.presentation.ui.cardsInformation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.flashcards.data.model.Card
import com.example.flashcards.databinding.CardsInformationBinding
import com.example.flashcards.navigation.addInformation.cardsInformation.CardsInformationNavigation.Companion.CARD_ID
import com.example.flashcards.presentation.ui.cardsInformation.adapter.CardsInformationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardsInformationActivity : AppCompatActivity() {
    private lateinit var binding: CardsInformationBinding
    private val viewModel: CardsInformationViewModel by viewModel()
    private val cardId: Int by lazy { intent.getIntExtra(CARD_ID, 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = CardsInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindObserver()

        viewModel.showCard(cardId)
    }

    private fun bindObserver() {
        viewModel.state.observe(this) {
            setDefaultState()
            when (it) {
                CardsInformationState.Error -> showErrorScreen()
                CardsInformationState.Loading -> showLoadingScreen()
                is CardsInformationState.Success -> showSuccessScreen(it.result)
            }
        }
    }

    private fun setDefaultState() {
        binding.recyclerView.isVisible = false
        binding.stateError.root.isVisible = false
        binding.stateLoading.root.isVisible = false
    }

    private fun showErrorScreen() {
        binding.stateError.root.isVisible = true
        binding.stateError.firstText.text = "Erro ao trazer o card"
    }

    private fun showLoadingScreen() {
        binding.stateLoading.root.isVisible = true
    }

    private fun showSuccessScreen(cards: List<Card>) {
        binding.recyclerView.isVisible = true
        binding.recyclerView.adapter = CardsInformationAdapter(cards)
    }
}