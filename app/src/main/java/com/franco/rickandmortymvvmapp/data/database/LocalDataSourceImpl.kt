package com.franco.rickandmortymvvmapp.data.database

import com.franco.rickandmortymvvmapp.data.domain.Character
import com.franco.rickandmortymvvmapp.data.fromDbToDomainModel
import com.franco.rickandmortymvvmapp.data.toDatabaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
        private val db:CharactersDatabase
): LocalDataSource {



    override suspend fun size(): Int =
         db.CharacterDAO().characterCount()


    override suspend fun saveCharactersToDb(characters: List<Character>) {
        db.CharacterDAO().insertCharacter(characters.map { it.toDatabaseModel() })
    }

    override fun getAllDatabaseCharacters(): Flow<List<Character>> =
            db.CharacterDAO().getAllCharacters()?.map { characters ->
                characters.map { it.fromDbToDomainModel() }
            }
}