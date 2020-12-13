package com.franco.rickandmortymvvmapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Characters")
data class Character(

    @PrimaryKey(autoGenerate = false)
    val id:Int,
    val image: String,
    val name: String,
    val species: String,
    @SerializedName("url")
    val characterDetailUrl: String
    )
