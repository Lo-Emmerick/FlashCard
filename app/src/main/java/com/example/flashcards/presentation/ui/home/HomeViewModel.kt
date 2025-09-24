package com.example.flashcards.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcards.domain.useCase.searchCard.SearchCardUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val searchCardUseCase: SearchCardUseCase
) : ViewModel() {

    private val _state = MutableLiveData<HomeState>()
    val state: LiveData<HomeState> = _state

    fun searchCard() {
        _state.value = HomeState.Loading
        viewModelScope.launch {
            try {
                val response = searchCardUseCase()
                if (response.isEmpty()) {
                    _state.value = HomeState.Empty
                } else {
                    _state.value = HomeState.Success(response)
                }
            } catch (e: Exception) {
                _state.value = HomeState.Error
            }
        }
    }
}