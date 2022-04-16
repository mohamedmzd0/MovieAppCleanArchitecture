package com.mohamed.movieappcleanarchitecture.data.main_movies.remote.api

import com.mohamed.movieappcleanarchitecture.domain.main_movies.entity.MoivesResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("popular?query=star+wars&page=1")
    suspend fun getMovies(@Query("api_key") apiKey: String): MoivesResponse
}