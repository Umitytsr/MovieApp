package com.umitytsr.movieapp.data.model.favorite

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Favorite (
    val backdropPath: String?,
    val genreİds: List<Int>?,
    val id: Int?,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String,
    val voteAverage: Double?,
): Parcelable