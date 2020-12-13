package com.franco.rickandmortymvvmapp.data.database

import com.franco.rickandmortymvvmapp.data.domain.Character
import com.franco.rickandmortymvvmapp.data.fromDbToDomainModel
import com.franco.rickandmortymvvmapp.data.toDatabaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterDatabaseSource(db:CharactersDatabase) :LocalDataSource{
    private val characterDao= db.CharacterDAO()
    override suspend fun size(): Int =
        characterDao.characterCount()


    override suspend fun saveCharactersToDb(characters: List<Character>) {
        characterDao.insertCharacter(characters.map {
            it.toDatabaseModel()
        })
    }

    override fun getAllDatabaseCharacters(): Flow<List<Character>> =
        characterDao.getAllCharacters().map { characters ->
            characters.map { it.fromDbToDomainModel() }

        }

    }
