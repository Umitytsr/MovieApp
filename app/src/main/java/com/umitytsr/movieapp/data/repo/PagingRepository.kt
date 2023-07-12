package com.umitytsr.movieapp.data.repo

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.umitytsr.movieapp.data.model.movie.ResultMovie
import com.umitytsr.movieapp.data.model.series.ResultTvSeries
import com.umitytsr.movieapp.data.paging.MoviePagingSource
import com.umitytsr.movieapp.data.paging.SeriesPagingSource
import com.umitytsr.movieapp.data.service.MovieAppService
import com.umitytsr.movieapp.util.Constants
import javax.inject.Inject

class PagingRepository @Inject constructor(private val movieAppService: MovieAppService) {

    fun getAllMoviesPaging(): LiveData<PagingData<ResultMovie>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                MoviePagingSource(movieAppService)
            }, initialKey = 1
        ).liveData
    }

    fun getAllTvSeriesPaging(): LiveData<PagingData<ResultTvSeries>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                SeriesPagingSource(movieAppService)
            }
            , initialKey = 1
        ).liveData
    }
}