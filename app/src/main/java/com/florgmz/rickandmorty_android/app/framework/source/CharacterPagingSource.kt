package com.florgmz.rickandmorty_android.app.framework.source

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.florgmz.rickandmorty_android.app.framework.model.SingleCharacter
import com.florgmz.rickandmorty_android.core.service.ApiService

class CharacterPagingSource (private val apiService: ApiService) : PagingSource<Int, SingleCharacter>() {
    override fun getRefreshKey(state: PagingState<Int, SingleCharacter>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SingleCharacter> {
        return try {
            val nextPage : Int = params.key ?: FIRST_PAGE_INDEX
            val response = apiService.getCharactersList().body()
            val nextPageNumber = if(response?.results?.isEmpty()!!) {
                null
            } else {
                nextPage + (params.loadSize / 20)
            }
            LoadResult.Page(data = response.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPageNumber)

        } catch(e: Exception){
            LoadResult.Error(e)
        }
    }

    companion object{
        private const val FIRST_PAGE_INDEX = 1
    }
}
