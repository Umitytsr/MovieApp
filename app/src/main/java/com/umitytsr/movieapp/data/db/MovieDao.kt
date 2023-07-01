package com.umitytsr.movieapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.umitytsr.movieapp.data.model.movie.MovieResponse
import com.umitytsr.movieapp.data.model.series.SeriesResponse

@Dao
interface MovieDao {
    // an insert is used to register to the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovieProperties(propertiesMovie: MovieResponse)

    // a query is used to retrieve the data stored in the database
    @Query("SELECT * FROM movie_response")
    suspend fun getAllMovie(): MovieResponse

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTvSerieProperties(propertiesTvSeries: SeriesResponse)

    @Query("SELECT * FROM tv_series_response")
    suspend fun getAllTvSeries() : SeriesResponse

}