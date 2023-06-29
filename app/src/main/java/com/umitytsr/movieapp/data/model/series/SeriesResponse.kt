package com.umitytsr.movieapp.data.model.series


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tv_series_response")
data class SeriesResponse(
    @SerializedName("page")
    val page: Int,
    @Embedded
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @PrimaryKey(autoGenerate = true)
    val idTvSeries: Int = 1,
)