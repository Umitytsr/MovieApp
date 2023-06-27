package com.umitytsr.movieapp.data.service


import com.umitytsr.movieapp.data.model.movie.MovieResponse
import com.umitytsr.movieapp.util.Constants
import retrofit2.http.GET

interface MovieAppService {
    @GET(Constants.MOVIE)
    suspend fun getAllMovie() : MovieResponse
}