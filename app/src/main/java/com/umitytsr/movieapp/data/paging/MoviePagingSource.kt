package com.umitytsr.movieapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.umitytsr.movieapp.data.model.movie.ResultMovie
import com.umitytsr.movieapp.data.service.MovieAppService

class MoviePagingSource(private val movieAppService: MovieAppService): PagingSource<Int, ResultMovie>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultMovie> {
        return try {
            val position = params.key ?: 1
            val response = movieAppService.getAllMoviePaging(position)
            LoadResult.Page(data = response.results, prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, ResultMovie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}