package com.franco.rickandmortymvvmapp.data.database

import com.franco.rickandmortymvvmapp.data.domain.Character
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl : LocalDataSource {
    override suspend fun size(): Int {
        TODO("Not yet implemented")
    }

    override suspend fun saveCharactersToDb(characters: List<Character>) {
        TODO("Not yet implemented")
    }

    override fun getAllDatabaseCharacters(): Flow<List<Character>> {
        TODO("Not yet implemented")
    }
}