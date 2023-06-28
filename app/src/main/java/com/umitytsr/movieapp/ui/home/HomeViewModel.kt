package com.umitytsr.movieapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.umitytsr.movieapp.data.model.movie.MovieResponse

import com.umitytsr.movieapp.data.model.movie.ResultMovie
import com.umitytsr.movieapp.data.repo.MovieAppRepository
import com.umitytsr.movieapp.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieAppRepository: MovieAppRepository)  : ViewModel() {

    private val _propertiesMovie = MutableStateFlow<List<Movie>>(mutableListOf())
    val properties : StateFlow<List<Movie>> = _propertiesMovie.asStateFlow()

    init {
        getMovieData()
    }

    private fun getMovieData() {
        viewModelScope.launch {
            movieAppRepository.fetchAllMovie().collect{
                _propertiesMovie.emit(it)
            }
        }
    }
}