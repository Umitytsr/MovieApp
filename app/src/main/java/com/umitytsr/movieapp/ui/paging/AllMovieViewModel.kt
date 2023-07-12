package com.umitytsr.movieapp.ui.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.umitytsr.movieapp.data.model.movie.ResultMovie
import com.umitytsr.movieapp.data.model.series.ResultTvSeries
import com.umitytsr.movieapp.data.repo.PagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AllMovieViewModel @Inject constructor(private val pagingRepository: PagingRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()

    fun getMovieList(): LiveData<PagingData<ResultMovie>> {
        return pagingRepository.getAllMoviesPaging().cachedIn(viewModelScope)
    }

    fun getTvSeriesList(): LiveData<PagingData<ResultTvSeries>> {
        return pagingRepository.getAllTvSeriesPaging().cachedIn(viewModelScope)
    }

}