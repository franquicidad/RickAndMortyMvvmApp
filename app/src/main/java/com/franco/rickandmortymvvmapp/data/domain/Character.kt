package com.franco.rickandmortymvvmapp.data.domain

import com.google.gson.annotations.SerializedName

data class Character(
    val id:Int,
    val image: String,
    val name: String,
    val species: String,
    @SerializedName("url")
    val characterDetailUrl: String
)
