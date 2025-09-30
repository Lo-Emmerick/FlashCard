package com.example.flashcards.presentation.ui.cardsInformation.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.flashcards.data.model.Card
import com.example.flashcards.databinding.ItemCardBinding

class CardsInformationViewHolder(
    private val binding: ItemCardBinding
) : ViewHolder(binding.root) {

    fun bind(item: Card) {
        bindView(item)
    }

    private fun bindView(item: Card) {
        binding.informationCard.text = item.first_text
    }
}