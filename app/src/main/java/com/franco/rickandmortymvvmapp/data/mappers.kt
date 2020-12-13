package com.franco.rickandmortymvvmapp.data



import com.franco.rickandmortymvvmapp.data.domain.Character
import com.franco.rickandmortymvvmapp.data.network.Character as NetworkModel
import com.franco.rickandmortymvvmapp.data.database.Character as DatabaseModel


fun NetworkModel.toDomainModel(): Character =

    Character(
        id,
        image,
        name,
        species,
        characterDetailUrl
    )

fun  Character.toDatabaseModel(): DatabaseModel=
    DatabaseModel(id, image, name, species, characterDetailUrl)

fun DatabaseModel.fromDbToDomainModel(): Character=
    Character(id, image, name, species, characterDetailUrl)