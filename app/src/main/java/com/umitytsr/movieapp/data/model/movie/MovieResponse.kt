package com.umitytsr.movieapp.data.model.movie

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_response")
data class MovieResponse(
    @SerializedName("page")
    val page: Int,
    @Embedded
    @SerializedName("results")
    val results: List<ResultMovie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @PrimaryKey(autoGenerate = true)
    val idMovie : Int = 1
)