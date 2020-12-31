package com.franco.rickandmortymvvmapp.data.domain

import com.franco.rickandmortymvvmapp.data.database.LocalDataSourceImpl
import com.franco.rickandmortymvvmapp.data.network.RemoteDataSourceImpl
import kotlinx.coroutines.flow.Flow

class RepositoryImpl  (
    private val localDataSource: LocalDataSourceImpl,
    private val remoteDataSource: RemoteDataSourceImpl
):Repository {
    companion object{
        const val  PAGE_SIZE = 20
        const val PAGE_THRESHOLD =10
    }
    override fun getCharactersRepo():Flow<List<Character>>
    = localDataSource.getAllDatabaseCharacters()

    override fun getListByQuery(query: String): Flow<List<Character>> =
            localDataSource.getListByQuery(query)

    override suspend fun checkRequireNewPage(lastVisible: Int) {
        val size = localDataSource.size()
        if (lastVisible >= size - PAGE_THRESHOLD) {
            val page = size / PAGE_SIZE + 1
            val newCharacters = remoteDataSource.getCharacters(page)
            localDataSource. saveCharactersToDb(newCharacters)
        }
    }
}