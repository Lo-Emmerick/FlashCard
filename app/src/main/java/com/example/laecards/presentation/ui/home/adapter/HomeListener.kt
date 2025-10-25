package com.example.laecards.presentation.ui.home.adapter

import com.example.laecards.data.model.Card

interface HomeListener {
    fun onClickItem(item: Card)
}