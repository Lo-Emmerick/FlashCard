package com.example.flashcards.presentation.ui.home

import com.example.flashcards.data.model.Card

sealed interface HomeState {
    data class Success(val result: List<Card>) : HomeState
    object Loading : HomeState
    object Empty : HomeState
    object Error : HomeState
}