package com.umitytsr.movieapp.domain.model

data class TvSeries(
    val backdropPath: String?,
    val firstAirDate: String?,
    val genreİds: List<Int>?,
    val id: Int?,
    val name: String?,
    val overview: String?,
    val posterPath: String?,
    val voteAverage: Double?,
)