package com.umitytsr.movieapp.di

import android.content.Context
import androidx.room.Room
import com.umitytsr.movieapp.data.db.MovieDao
import com.umitytsr.movieapp.data.db.MovieDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideMovieDb(@ApplicationContext context: Context): MovieDb{
        return Room.databaseBuilder(
            context,
            MovieDb::class.java,
            "movie_database",
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(movieDb: MovieDb): MovieDao{
        return movieDb.moviePropertyDao()
    }
}