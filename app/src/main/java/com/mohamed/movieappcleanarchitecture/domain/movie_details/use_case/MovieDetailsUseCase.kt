package com.mohamed.movieappcleanarchitecture.domain.movie_details.use_case

import com.mohamed.movieappcleanarchitecture.domain.movie_details.MovieDetailsRepo
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(private val movieDetailsRepo: MovieDetailsRepo) {
    suspend fun getMovie(id: Int) = movieDetailsRepo.getMoveByID(id)
}