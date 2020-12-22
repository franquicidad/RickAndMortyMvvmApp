package com.franco.rickandmortymvvmapp.data.domain

import com.franco.rickandmortymvvmapp.data.network.RMService
import retrofit2.HttpException
import java.io.IOException
import androidx.paging.PagingSource
import com.franco.rickandmortymvvmapp.data.network.Character

private const val GITHUB_STARTING_PAGE_INDEX = 1

class PagingSource(
        private val service: RMService,
) : PagingSource<Int, Character>() {
    override suspend fun load(params:LoadParams<Int>): LoadResult<Int, Character> {
        val position = params.key ?: GITHUB_STARTING_PAGE_INDEX

        return try {
            val response = service.listOfCharacters(position)
            val repos = response.characters
            LoadResult.Page(
                    data = repos,
                    prevKey = if (position == GITHUB_STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = if (repos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}
