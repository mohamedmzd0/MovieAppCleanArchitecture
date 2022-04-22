package com.mohamed.movieappcleanarchitecture.domain.main_movies.use_case

import com.mohamed.movieappcleanarchitecture.domain.main_movies.MoviesRepository
import javax.inject.Inject

class MoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {
    suspend fun getMovies(page:Int) = moviesRepository.getMovies(page)
}