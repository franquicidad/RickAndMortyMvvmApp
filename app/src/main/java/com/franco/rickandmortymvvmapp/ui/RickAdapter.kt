package com.franco.rickandmortymvvmapp.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.franco.rickandmortymvvmapp.R
import com.franco.rickandmortymvvmapp.data.domain.Character
import com.franco.rickandmortymvvmapp.databinding.ViewCharacterBinding
import com.franco.rickandmortymvvmapp.loadUrl
import com.franco.rickandmortymvvmapp.ui.home.HomeViewModel
import kotlin.properties.Delegates

class RickAdapter (
    characters:List<Character> = emptyList()
        ): RecyclerView.Adapter<RickAdapter.ViewHolder>() {

    //lateinit var mItemCLicked: ItemCLickedListener

    var navController: NavController? = null


    var characters: List<Character> by Delegates.observable(characters){
        _ , _ , _ ->notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.view_character,parent,false
        ))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = characters[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            Log.i("Adapter","Clicked")
            val bundle = bundleOf(
                "characterName" to item.name,
                "species" to item.species,
                "image" to item.image
            )
            navController = Navigation.findNavController(it)
            navController!!.navigate(R.id.action_navigation_home_to_detailMortyFragment, bundle)

        }
    }
//    fun setUpListener(itemCLicked: ItemCLickedListener) {
//        mItemCLicked = itemCLicked
//    }
//    interface ItemCLickedListener {
//        fun onItemClicked(modelItem: Character)
//    }

    override fun getItemCount(): Int = characters.size

     class ViewHolder(
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