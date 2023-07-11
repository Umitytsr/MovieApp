package com.umitytsr.movieapp.data.source.local

import com.umitytsr.movieapp.data.db.MovieDao
import com.umitytsr.movieapp.data.model.favorite.Favorite
import com.umitytsr.movieapp.data.model.movie.MovieResponse
import com.umitytsr.movieapp.data.model.series.SeriesResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieAppLocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    suspend fun insertMovieProperties(movieProperties: MovieResponse){
        movieDao.insertAllMovieProperties(movieProperties)
    }

    suspend fun getAllMoviePropertiesFromDb(): MovieResponse {
        return movieDao.getAllMovie()
    }

    suspend fun insertTvSeriesProperties(tvSeriesProperties: SeriesResponse){
        movieDao.insertAllTvSerieProperties(tvSeriesProperties)
    }

    suspend fun getAllTvSeriesPropertiesFromDb(): SeriesResponse {
        return movieDao.getAllTvSeries()
    }

    suspend fun insertFavoriteProperties(favoriteProperties: Favorite){
        movieDao.insertFavoriteProperties(favoriteProperties)
    }

    suspend fun deleteFavoriteProperties(favoriteProperties: Favorite){
        movieDao.deleteFavorite(favoriteProperties)
    }

    suspend fun getAllFavoritePropertiesFromDb(): List<Favorite> {
        return movieDao.getAllFavorite()
    }

    suspend fun isFavorite(id: Int) : Boolean{
        return movieDao.isFavorite(id)
    }
}