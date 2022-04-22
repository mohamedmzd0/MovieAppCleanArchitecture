package com.mohamed.movieappcleanarchitecture.data.movie_details.remote.api

import com.mohamed.movieappcleanarchitecture.domain.movie_details.entity.MovieDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailsApi {

    @GET("{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
    ): MovieDetailsResponse
}