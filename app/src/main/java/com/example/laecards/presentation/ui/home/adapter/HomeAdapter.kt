package com.example.laecards.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.laecards.data.model.Card
import com.laecards.app.databinding.ItemHomeBinding

class HomeAdapter(
    private val items: List<Card>,
    private val listener: HomeListener
) : Adapter<HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(items[position])
    }
}