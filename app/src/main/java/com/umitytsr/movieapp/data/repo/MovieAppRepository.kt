package com.umitytsr.movieapp.data.repo

import android.content.Context
import com.umitytsr.movieapp.data.model.favorite.Favorite
import com.umitytsr.movieapp.data.source.local.MovieAppLocalDataSource
import com.umitytsr.movieapp.data.source.remote.MovieAppRemoteDataSource
import com.umitytsr.movieapp.domain.Extensions.toMovie
import com.umitytsr.movieapp.domain.Extensions.toTvSeries
import com.umitytsr.movieapp.domain.model.Movie
import com.umitytsr.movieapp.util.CheckInternet
import com.umitytsr.movieapp.util.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class MovieAppRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val remoteDataSource: MovieAppRemoteDataSource,
    private val localDataSource: MovieAppLocalDataSource
    ) {

    suspend fun fetchAllMovie():Flow<Resource<List<Movie>>> = flow {
        Resource.Loading
        try {
            if (CheckInternet.isInternetAvailable(context)){
                val propertiesMovieFromApi = remoteDataSource.getAllMovieProperties()
                localDataSource.insertMovieProperties(propertiesMovieFromApi)
                val changeMovie = propertiesMovieFromApi.results.toMovie()
                emit(Resource.Success(changeMovie))
            } else {
                val localMovie = localDataSource.getAllMoviePropertiesFromDb().results.toMovie()
                emit(Resource.Success(localMovie))
            }
        }catch (e:Exception){
            Resource.Error(e)
        }
    }

    suspend fun fetchAllTvSeries(): Flow<Resource<List<Movie>>> = flow {
        Resource.Loading
        try {
            if (CheckInternet.isInternetAvailable(context)) {
                val propertiesTvSeriesFromApi = remoteDataSource.getAllTvSeriesProperties()
                localDataSource.insertTvSeriesProperties(propertiesTvSeriesFromApi)
                val changeTvSerie = propertiesTvSeriesFromApi.resultTvSeries.toTvSeries()
                emit(Resource.Success(changeTvSerie))
            } else {
                val localTvSerie = localDataSource.getAllTvSeriesPropertiesFromDb().resultTvSeries.toTvSeries()
                emit(Resource.Success(localTvSerie))
            }
        }catch (e:Exception){
            Resource.Error(e)
        }

    }

    fun allFavorites(): Flow<List<Favorite>> = flow {
        emit(localDataSource.getAllFavoritePropertiesFromDb())
    }

    suspend fun addMovieToFavorites(favorite: Favorite) {
        localDataSource.insertFavoriteProperties(favorite)
    }

    suspend fun removeMovieFromFavorites(favorite: Favorite) {
        localDataSource.deleteFavoriteProperties(favorite)
    }

    fun isFavorite(id: Int): Flow<Boolean> = flow {
        emit(localDataSource.isFavorite(id))
    }
}