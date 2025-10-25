package com.example.laecards.presentation.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.laecards.data.model.Card
import com.laecards.app.databinding.ItemHomeBinding

class HomeViewHolder(
    private val binding: ItemHomeBinding,
    private val listener: HomeListener
) : ViewHolder(binding.root) {

    fun bind(item: Card) {
        bindView(item)
        bindClick(item)
    }

    private fun bindView(item: Card) {
        binding.informationCard.text = item.first_text
    }

    private fun bindClick(item: Card) {
        binding.root.setOnClickListener {
            listener.onClickItem(item)
        }
    }
}