package com.umitytsr.movieapp.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val voteAverage: Double,
)