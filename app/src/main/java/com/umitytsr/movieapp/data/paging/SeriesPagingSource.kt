package com.umitytsr.movieapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.umitytsr.movieapp.data.model.series.ResultTvSeries
import com.umitytsr.movieapp.data.service.MovieAppService

class SeriesPagingSource(private val movieAppService: MovieAppService)
    : PagingSource<Int, ResultTvSeries>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultTvSeries> {
        return try {
            val position = params.key ?: 1
            val response = movieAppService.getAllTvSeriesPaging(position)
            LoadResult.Page(data = response.resultTvSeries, prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, ResultTvSeries>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}