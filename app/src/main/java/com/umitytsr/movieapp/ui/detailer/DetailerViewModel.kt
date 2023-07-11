package com.umitytsr.movieapp.ui.detailer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umitytsr.movieapp.data.model.favorite.Favorite
import com.umitytsr.movieapp.data.repo.MovieAppRepository
import com.umitytsr.movieapp.domain.Extensions.toFavorite
import com.umitytsr.movieapp.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailerViewModel @Inject constructor(private val movieAppRepository: MovieAppRepository) :
    ViewModel() {

    private val _propertiesFavorite = MutableStateFlow<List<Favorite>>(listOf())
    val propertiesFavorite: StateFlow<List<Favorite>> = movieAppRepository
        .allFavorites()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = listOf()
        )

    fun addMovieToFavorites(movie: Movie) {
        viewModelScope.launch {
            val favorite = movie.toFavorite()
            movieAppRepository.addMovieToFavorites(favorite)
        }
    }

    fun removeMovieFromFavorites(movie: Movie) {
        viewModelScope.launch {
            val favorite = movie.toFavorite()
            movieAppRepository.removeMovieFromFavorites(favorite)
        }
    }

    fun refreshFavorites() {
        viewModelScope.launch {
            movieAppRepository.allFavorites().collect {
                _propertiesFavorite.emit(it)
            }
        }
    }

    private val _isFavorite = MutableStateFlow<Boolean>(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    fun initIsFavorite(id: Int) {
        viewModelScope.launch {
            movieAppRepository.isFavorite(id).collect {
                _isFavorite.emit(it)
            }
        }
    }
}