package com.franco.rickandmortymvvmapp

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.franco.rickandmortymvvmapp.data.database.CharactersDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RickAndMortyApp :Application()

