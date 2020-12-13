package com.franco.rickandmortymvvmapp.data.network


import com.franco.rickandmortymvvmapp.data.network.models.Info
import com.franco.rickandmortymvvmapp.data.network.models.Location
import com.franco.rickandmortymvvmapp.data.network.models.Origin
import com.google.gson.annotations.SerializedName

data class NetworkModel(
    val info: Info,
    @SerializedName("results")
    val characters: List<Character>
)

data class Character(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    @SerializedName("url")
    val characterDetailUrl: String
)