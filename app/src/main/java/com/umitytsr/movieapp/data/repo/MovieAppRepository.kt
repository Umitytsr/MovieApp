package com.umitytsr.movieapp.data.repo

import android.content.Context
import com.umitytsr.movieapp.data.source.local.MovieAppLocalDataSource
import com.umitytsr.movieapp.data.source.remote.MovieAppRemoteDataSource
import com.umitytsr.movieapp.domain.Extensions.toMovie
import com.umitytsr.movieapp.domain.Extensions.toTvSeries
import com.umitytsr.movieapp.domain.model.Movie
import com.umitytsr.movieapp.domain.model.TvSeries
import com.umitytsr.movieapp.util.CheckInternet
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieAppRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val remoteDataSource: MovieAppRemoteDataSource,
    private val localDataSource: MovieAppLocalDataSource
    ) {

    suspend fun fetchAllMovie():Flow<List<Movie>> = flow {
        if (CheckInternet.isInternetAvailable(context)){
            val propertiesMovieFromApi = remoteDataSource.getAllMovieProperties()
            localDataSource.insertMovieProperties(propertiesMovieFromApi)
            val changeMovie = remoteDataSource.getAllMovieProperties().results.toMovie()
            emit(changeMovie)
        }else{
            val localMovie = localDataSource.getAllMoviePropertiesFromDb().results.toMovie()
            emit(localMovie)
        }
    }

    suspend fun fetchAllTvSeries():Flow<List<TvSeries>> = flow {
        if (CheckInternet.isInternetAvailable(context)){
            val propertiesTvSeriesFromApi = remoteDataSource.getAllTvSeriesProperties()
            localDataSource.insertTvSeriesProperties(propertiesTvSeriesFromApi)
            val changeTvSerie = remoteDataSource.getAllTvSeriesProperties().results.toTvSeries()
            emit(changeTvSerie)
        }else{
            val localTvSerie = localDataSource.getAllTvSeriesPropertiesFromDb().results.toTvSeries()
            emit(localTvSerie)
        }
    }
}