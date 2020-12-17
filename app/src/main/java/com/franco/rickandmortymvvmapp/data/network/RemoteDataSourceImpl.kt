package com.franco.rickandmortymvvmapp.data.network

import com.franco.rickandmortymvvmapp.data.domain.Character
import com.franco.rickandmortymvvmapp.data.toDomainModel

class RemoteDataSourceImpl(

) : RemoteDataSource {
    override suspend fun getCharacters(page: Int): List<Character> =
      RickAndMortyApi.service
          .listOfCharacters(page)
          .characters
          .map { it.toDomainModel() }

}