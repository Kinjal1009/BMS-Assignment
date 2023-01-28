package com.example.bmsAssignment.base.api

import com.example.bmsAssignment.model.MovieScheduleResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getMovieVenusList(): Response<MovieScheduleResponse>
}