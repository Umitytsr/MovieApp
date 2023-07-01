package com.umitytsr.movieapp.data.source.remote

import com.umitytsr.movieapp.data.service.MovieAppService
import javax.inject.Inject

class MovieAppRemoteDataSource @Inject constructor(private val movieAppService:MovieAppService) {
    suspend fun getAllMovieProperties() = movieAppService.getAllMovie()
    suspend fun getAllTvSeriesProperties() = movieAppService.getAllTvSeries()
}