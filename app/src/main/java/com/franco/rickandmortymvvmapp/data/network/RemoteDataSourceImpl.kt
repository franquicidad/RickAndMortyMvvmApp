package com.franco.rickandmortymvvmapp.data.network

import com.franco.rickandmortymvvmapp.data.domain.Character
import com.franco.rickandmortymvvmapp.data.toDomainModel
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
        private val service:RMService
) : RemoteDataSource {
    override suspend fun getCharacters(page: Int): List<Character> =
            service.listOfCharacters(page).characters.map { it.toDomainModel() }
}