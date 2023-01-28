package com.example.bmsAssignment.base.api

import com.example.bmsAssignment.model.MovieScheduleResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiServices) : ApiHelper {

    override suspend fun getMovieVenusList(): Response<MovieScheduleResponse> = apiService.getMovieVenusList()
}