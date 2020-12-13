package com.franco.rickandmortymvvmapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Character::class],version = 1)
abstract class CharactersDatabase : RoomDatabase() {
    abstract fun CharacterDAO():CharacterDAO
}