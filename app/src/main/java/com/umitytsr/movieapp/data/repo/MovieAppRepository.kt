package com.umitytsr.movieapp.data.repo

import com.umitytsr.movieapp.data.service.MovieAppService
import com.umitytsr.movieapp.domain.Extensions.toMovie
import com.umitytsr.movieapp.domain.Extensions.toTvSeries
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieAppRepository @Inject constructor(private val movieAppService: MovieAppService) {

    suspend fun fetchAllMovie() = flow {
        val responseMovie = movieAppService.getAllMovie().results.toMovie()
        emit(responseMovie)
    }

    suspend fun fetchAllTvSeries() = flow {
        val responseTvSeries = movieAppService.getAllTvSeries().results.toTvSeries()
        emit(responseTvSeries)
    }
}