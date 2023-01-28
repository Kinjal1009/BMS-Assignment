package com.example.bmsAssignment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmsAssignment.base.api.NetworkCall
import com.example.bmsAssignment.model.MovieScheduleResponse
import com.example.bmsAssignment.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    private val mApiCall = MutableLiveData<NetworkCall<MovieScheduleResponse>>()

    val apiCall: LiveData<NetworkCall<MovieScheduleResponse>>
        get() = mApiCall

    init {
        getMovieVenues()
    }

    private fun getMovieVenues() = viewModelScope.launch {
        mApiCall.postValue(NetworkCall.loading(null))
        movieRepository.callMovieRepository().let {
            if (it.isSuccessful) {
                mApiCall.postValue(NetworkCall.success(it.body()))
            } else {
                mApiCall.postValue(NetworkCall.error(it.errorBody().toString(), null))
            }
        }
    }
}