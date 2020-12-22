package com.franco.rickandmortymvvmapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.franco.rickandmortymvvmapp.R
import com.franco.rickandmortymvvmapp.collectFlow
import com.franco.rickandmortymvvmapp.data.domain.Character
import com.franco.rickandmortymvvmapp.databinding.ViewCharacterBinding
import com.franco.rickandmortymvvmapp.loadUrl
import com.franco.rickandmortymvvmapp.onClickEvents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class PagingAdapter (private val scope: CoroutineScope) :
    ListAdapter<Character, PagingAdapter.ItemViewHolder>(DiffCallBackFromAdapter()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_character,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = with(holder){
        val item =getItem(position)
        bind(item)
        scope.collectFlow(itemView.onClickEvents){

        }
    }


    class ItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val binding = ViewCharacterBinding.bind(itemView)
        fun bind(item: Character) = with(binding) {
            characterName.text = item.name
            characterImage.loadUrl(item.image)

        }
    }

}

class DiffCallBackFromAdapter :DiffUtil.ItemCallback<Character>(){
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }

}