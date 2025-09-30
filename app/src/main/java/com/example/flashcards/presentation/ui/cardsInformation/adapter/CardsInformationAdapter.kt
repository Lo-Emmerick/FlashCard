package com.example.flashcards.presentation.ui.cardsInformation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.flashcards.data.model.Card
import com.example.flashcards.databinding.ItemCardBinding

class CardsInformationAdapter(
    private val items: List<Card>
) : Adapter<CardsInformationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsInformationViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardsInformationViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: CardsInformationViewHolder, position: Int) {
        holder.bind(items[position])
    }
}