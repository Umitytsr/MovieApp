package com.umitytsr.movieapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umitytsr.movieapp.data.repo.MovieAppRepository
import com.umitytsr.movieapp.domain.model.Movie
import com.umitytsr.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieAppRepository: MovieAppRepository)  : ViewModel() {

    private val _propertiesMovie = MutableStateFlow<Resource<List<Movie>>>(Resource.Loading)
    val propertiesMovie : StateFlow<Resource<List<Movie>>> = _propertiesMovie.asStateFlow()

    private val _propertiesTvSeries = MutableStateFlow<Resource<List<Movie>>>(Resource.Loading)
    val propertiesTvSeries : StateFlow<Resource<List<Movie>>> = _propertiesTvSeries.asStateFlow()

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

    private fun getTvSeriesData(){
        viewModelScope.launch(Dispatchers.IO){
            movieAppRepository.fetchAllTvSeries().collect{
                _propertiesTvSeries.emit(it)
            }
        }
    }
}