package com.umitytsr.movieapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.umitytsr.movieapp.data.model.favorite.Favorite
import com.umitytsr.movieapp.data.model.movie.MovieResponse
import com.umitytsr.movieapp.data.model.movie.ResultMovie
import com.umitytsr.movieapp.data.model.series.ResultTvSeries
import com.umitytsr.movieapp.data.model.series.SeriesResponse
import com.umitytsr.movieapp.util.DataBaseConverter

@Database(
    entities = [MovieResponse::class,
        ResultMovie::class,
        SeriesResponse::class,
        ResultTvSeries::class,
        Favorite::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DataBaseConverter::class)
abstract class MovieDb: RoomDatabase() {
    abstract fun moviePropertyDao() : MovieDao
}