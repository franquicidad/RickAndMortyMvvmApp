package com.franco.rickandmortymvvmapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.franco.rickandmortymvvmapp.R
import com.franco.rickandmortymvvmapp.collectFlow
import com.franco.rickandmortymvvmapp.databinding.ViewCharacterBinding
import com.franco.rickandmortymvvmapp.onClickEvents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.franco.rickandmortymvvmapp.data.domain.Character

@ExperimentalCoroutinesApi
class RickMortyCharacterAdapter(private val scope: CoroutineScope) :
    ListAdapter<Character, RickMortyCharacterAdapter.ItemViewholder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_character, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
        scope.collectFlow(itemView.onClickEvents) {
            //switch to detail...
          //  itemView.context.toast(item.title)
        }
    }

    class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ViewCharacterBinding.bind(itemView)

        fun bind(item:Character) = with(binding) {
            characterName.text = item.name
            Glide
                .with(characterImage.context)
                .load(item.image)
                .into(characterImage)
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}