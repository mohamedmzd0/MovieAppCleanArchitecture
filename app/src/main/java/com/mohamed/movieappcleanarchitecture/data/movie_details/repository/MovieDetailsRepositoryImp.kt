package com.mohamed.movieappcleanarchitecture.data.movie_details.repository

import com.mohamed.movieappcleanarchitecture.data.movie_details.remote.api.MovieDetailsApi
import com.mohamed.movieappcleanarchitecture.domain.movie_details.MovieDetailsRepo
import com.mohamed.movieappcleanarchitecture.infrastructure.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDetailsRepositoryImp @Inject constructor(private val movieDetailsApi: MovieDetailsApi) :
    MovieDetailsRepo {
    override suspend fun getMoveByID(id: Int) =
        withContext(Dispatchers.IO) {
            movieDetailsApi.getMovieDetails(
                apiKey = Constants.API_KEY,
                id = id
            )
        }
}