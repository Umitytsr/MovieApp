package com.umitytsr.movieapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umitytsr.movieapp.data.model.movie.MovieResponse
import com.umitytsr.movieapp.data.model.series.SeriesResponse
import com.umitytsr.movieapp.data.repo.MovieAppRepository
import com.umitytsr.movieapp.domain.model.Movie
import com.umitytsr.movieapp.domain.model.TvSeries
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieAppRepository: MovieAppRepository)  : ViewModel() {

    private val _propertiesMovie = MutableStateFlow<List<Movie>>(mutableListOf())
    val propertiesMovie : StateFlow<List<Movie>> = _propertiesMovie.asStateFlow()

    init {
        getMovieData()
        getTvSeriesData()
    }

    private fun getMovieData() {
        viewModelScope.launch(Dispatchers.IO) {
            movieAppRepository.fetchAllMovie().collect{
                _propertiesMovie.emit(it)
            }
        }
    }

    private val _propertiesTvSeries = MutableStateFlow<List<TvSeries>>(mutableListOf())
    val propertiesTvSeries : StateFlow<List<TvSeries>> = _propertiesTvSeries.asStateFlow()

    private fun getTvSeriesData(){
        viewModelScope.launch(Dispatchers.IO){
            movieAppRepository.fetchAllTvSeries().collect{
                _propertiesTvSeries.emit(it)
            }
        }
    }

}