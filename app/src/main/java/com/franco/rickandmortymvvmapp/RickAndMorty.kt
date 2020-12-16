package com.franco.rickandmortymvvmapp

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.franco.rickandmortymvvmapp.data.database.CharactersDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RickAndMorty :Application() {
    lateinit var db: CharactersDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            this,
            CharactersDatabase::class.java,
            "character-db"
        ).build()
    }
}

