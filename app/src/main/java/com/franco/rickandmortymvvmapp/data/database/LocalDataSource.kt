package com.franco.rickandmortymvvmapp.data.database

import com.franco.rickandmortymvvmapp.data.domain.Character
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun size():Int
    suspend fun saveCharactersToDb(characters :List<Character>)
     fun getAllDatabaseCharacters(): Flow<List<Character>>

}
