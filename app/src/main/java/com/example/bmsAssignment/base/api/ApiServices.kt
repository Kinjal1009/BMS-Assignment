package com.example.bmsAssignment.base.api

import com.example.bmsAssignment.model.MovieScheduleResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    companion object{
       const val BASE_URL = "https://demo2782755.mockable.io/"
    }

    @GET("movie_showtimes")
    suspend fun getMovieVenusList(): Response<MovieScheduleResponse>
}