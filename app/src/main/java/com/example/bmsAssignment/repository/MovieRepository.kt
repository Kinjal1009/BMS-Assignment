package com.example.bmsAssignment.repository

import com.example.bmsAssignment.base.api.ApiHelper
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun callMovieRepository() = apiHelper.getMovieVenusList()
}