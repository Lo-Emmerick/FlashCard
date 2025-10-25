package com.example.laecards.presentation.ui.addInformation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laecards.domain.data.CardEdit
import com.example.laecards.domain.useCase.addCard.AddCardUseCase
import kotlinx.coroutines.launch

class AddInformationViewModel(
    private val addCardUseCase: AddCardUseCase
) : ViewModel() {

    private val _state = MutableLiveData<AddInformationState>()
    val state: LiveData<AddInformationState> = _state

    fun addCard(card: CardEdit) {
        viewModelScope.launch {
            try {
                val response = addCardUseCase(card)
                if (response) {
                    _state.value = AddInformationState.Success
                } else {
                    _state.value = AddInformationState.Error
                }
            } catch (e: Exception) {
                _state.value = AddInformationState.Error
            }
        }
    }
}