package com.franco.rickandmortymvvmapp.data.network

import retrofit2.http.GET
import retrofit2.http.Query

//https://rickandmortyapi.com/api/character/?page=1
interface RMService {

    @GET("character/")
    suspend fun listOfCharacters(
        @Query("page") page:Int
    ): NetworkModel

}
