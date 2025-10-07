package com.example.flashcards.presentation.ui.cardsInformation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcards.domain.useCase.deleteCard.DeleteCardUseCase
import com.example.flashcards.domain.useCase.showCard.ShowCardUseCase
import com.example.flashcards.presentation.ui.cardsInformation.state.CardsInformationDeleteState
import com.example.flashcards.presentation.ui.cardsInformation.state.CardsInformationState
import kotlinx.coroutines.launch

class CardsInformationViewModel(
    private val showCardUseCase: ShowCardUseCase,
    private val deleteCardUseCase: DeleteCardUseCase
) : ViewModel() {

    private val _deteleState = MutableLiveData<CardsInformationDeleteState>()
    val deleteState: LiveData<CardsInformationDeleteState> get() = _deteleState

    private val _state = MutableLiveData<CardsInformationState>()
    val state: LiveData<CardsInformationState> get() = _state

    fun loadDeck(cardId: Int) {
        _state.value = CardsInformationState.Loading
        viewModelScope.launch {
            try {
                val firstCard = showCardUseCase.loadDeck(cardId)
                if (firstCard != null) {
                    _state.value = CardsInformationState.Success(firstCard)
                } else {
                    _state.value = CardsInformationState.Empty
                }
            } catch (e: Exception) {
                _state.value = CardsInformationState.Error
            }
        }
    }

    fun showNextCard() {
        viewModelScope.launch {
            try {
                val nextCard = showCardUseCase.advanceIndex()
                if (nextCard != null) {
                    _state.value = CardsInformationState.Success(nextCard)
                } else {
                    _state.value = CardsInformationState.Empty
                }
            } catch (e: Exception) {
                _state.value = CardsInformationState.Error
            }
        }
    }

    fun deleteCard(cardId: Int) {
        _deteleState.value = CardsInformationDeleteState.Loading
        viewModelScope.launch {
            val deleted = deleteCardUseCase(cardId)
            if (deleted) {
                showCardUseCase.removeCardFromList(cardId)
                val nextCard = showCardUseCase.getNextCard()
                if (nextCard != null) {
                    _state.value = CardsInformationState.Success(nextCard)
                    _deteleState.value = CardsInformationDeleteState.Success
                } else {
                    _deteleState.value = CardsInformationDeleteState.Empty
                }
            } else {
                _deteleState.value = CardsInformationDeleteState.Error
            }
        }
    }
}