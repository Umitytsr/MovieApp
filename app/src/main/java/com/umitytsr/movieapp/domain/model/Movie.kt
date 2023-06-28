package com.umitytsr.movieapp.domain.model

data class Movie(
    val backdropPath: String?,
    val genreİds: List<Int>?,
    val id: Int?,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String,
    val voteAverage: Double?,
)