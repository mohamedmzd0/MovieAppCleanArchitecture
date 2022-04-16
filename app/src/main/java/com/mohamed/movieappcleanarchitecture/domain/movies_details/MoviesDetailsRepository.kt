package com.mohamed.movieappcleanarchitecture.domain.movies_details

import com.mohamed.movieappcleanarchitecture.domain.movies_details.entity.MoiveDetailsResponse
import com.mohamed.movieappcleanarchitecture.infrastructure.utils.Resources
import kotlinx.coroutines.flow.Flow

interface MoviesDetailsRepository {
    suspend fun getMovieDetails(movieID: Int): Flow<Resources<MoiveDetailsResponse>>
}