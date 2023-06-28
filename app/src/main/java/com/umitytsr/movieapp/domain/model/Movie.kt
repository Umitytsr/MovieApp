package com.umitytsr.movieapp.domain.model

data class Movie(
    val backdropPath: String?,
    val genreİds: List<Int>?,
    val id: Int?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val voteAverage: Double?,
)