package com.franco.rickandmortymvvmapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.franco.rickandmortymvvmapp.R
import com.franco.rickandmortymvvmapp.data.domain.Character
import com.franco.rickandmortymvvmapp.databinding.ViewCharacterBinding
import com.franco.rickandmortymvvmapp.loadUrl
import com.franco.rickandmortymvvmapp.ui.home.HomeViewModel

class RickAdapter (
    private val characters:List<Character>
        ): RecyclerView.Adapter<RickAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.view_character,parent,false
        ))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int = characters.size


    inner class ViewHolder(
        val viewCharacter:ViewCharacterBinding
    ) : RecyclerView.ViewHolder(viewCharacter.root) {
        fun bind(character: Character) {
            val url =character.image
            val name=character.name

            viewCharacter.characterName.text=name
            viewCharacter.characterImage.loadUrl(url)



        }


    }
}