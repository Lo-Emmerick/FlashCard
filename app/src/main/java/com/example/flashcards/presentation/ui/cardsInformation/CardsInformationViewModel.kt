package com.example.flashcards.presentation.ui.cardsInformation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcards.domain.useCase.showCard.ShowCardUseCase
import kotlinx.coroutines.launch

class CardsInformationViewModel(
    private val showCardUseCase: ShowCardUseCase
) : ViewModel() {
    private val _state = MutableLiveData<CardsInformationState>()
    val state: LiveData<CardsInformationState> = _state

    fun showCard(cardId: Int) {
        _state.value = CardsInformationState.Loading
        viewModelScope.launch {
            try {
                val response = showCardUseCase(cardId)

                _state.value = CardsInformationState.Success(response)

            } catch (e: Exception) {
                _state.value = CardsInformationState.Error
            }
        }
    }
}