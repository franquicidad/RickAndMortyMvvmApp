package com.franco.rickandmortymvvmapp.data.domain

import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getCharactersRepo(): Flow<List<Character>>
    suspend fun checkRequireNewPage(lastVisible: Int)
}