package com.umitytsr.movieapp.data.model.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class Favorite(
    val backdropPath: String?,
    val genreİds: List<Int>?,
    @PrimaryKey()
    val id: Int?,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val voteAverage: Double?
)