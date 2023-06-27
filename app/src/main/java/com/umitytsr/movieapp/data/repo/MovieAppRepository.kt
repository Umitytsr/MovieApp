package com.umitytsr.movieapp.data.repo

import com.umitytsr.movieapp.data.service.MovieAppService
import com.umitytsr.movieapp.domain.Extensions.toMovie
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieAppRepository @Inject constructor(private val movieAppService: MovieAppService) {


    suspend fun getAllMovie() = flow {
        val response = movieAppService.getAllMovie().results.toMovie()
        emit(response)
    }
}