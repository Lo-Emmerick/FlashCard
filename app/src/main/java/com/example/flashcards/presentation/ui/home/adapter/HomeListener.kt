package com.example.flashcards.presentation.ui.home.adapter

import com.example.flashcards.data.model.Card

interface HomeListener {
    fun onClickItem(item: Card)
}