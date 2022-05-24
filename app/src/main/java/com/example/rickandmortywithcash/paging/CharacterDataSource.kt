package com.example.rickandmortywithcash.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortywithcash.model.Character
import com.example.rickandmortywithcash.service.Service
import java.lang.Exception

class CharacterDataSource(
    private val service: Service
) : PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = service.loadAllCharacters(nextPageNumber)
            LoadResult.Page(
                data = response.results,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < response.info.pages) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }
}