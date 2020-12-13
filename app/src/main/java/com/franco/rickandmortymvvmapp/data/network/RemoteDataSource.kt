package com.franco.rickandmortymvvmapp.data.network

import com.franco.rickandmortymvvmapp.data.domain.Character

interface RemoteDataSource {
    suspend fun  getCharacters(page:Int): List<Character>
}