package com.mohamed.movieappcleanarchitecture.domain.main_movies

import com.mohamed.movieappcleanarchitecture.domain.main_movies.entity.MoivesResponse
import com.mohamed.movieappcleanarchitecture.infrastructure.utils.Resources
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getMovies(): Flow<Resources<MoivesResponse>>
}