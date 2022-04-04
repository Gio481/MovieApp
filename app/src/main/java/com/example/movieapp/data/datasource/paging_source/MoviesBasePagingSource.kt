package com.example.movieapp.data.datasource.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.data.datasource.remote.dto.MoviesDTO
import com.example.movieapp.util.Constants.MOVIES_PAGE_SIZE
import com.example.movieapp.util.Constants.STARTING_PAGE_INDEX

abstract class MoviesBasePagingSource : PagingSource<Int, MoviesDTO>() {

    override fun getRefreshKey(state: PagingState<Int, MoviesDTO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesDTO> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX
        return try {
            val movies = moviesResponse(page = pageIndex)
            val nextKey = if (movies?.isEmpty()!!) {
                null
            } else {
                pageIndex + (params.loadSize / MOVIES_PAGE_SIZE)
            }
            LoadResult.Page(
                data = movies,
                prevKey = if (pageIndex == STARTING_PAGE_INDEX) null else pageIndex - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    abstract suspend fun moviesResponse(page: Int): List<MoviesDTO>?
}