package com.franco.rickandmortymvvmapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDAO {

    @Query("SELECT * FROM Characters")
    fun getAllCharacters(): Flow<List<Character>>

    @Query("SELECT * FROM Characters WHERE name LIKE  '%' ||  :query  ||  '%'  ")
    fun getListBySearchBar(query:String,): Flow<List<Character>>

    @Query("SELECT COUNT(id) FROM Characters")
    suspend fun characterCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character : List<Character>)
}