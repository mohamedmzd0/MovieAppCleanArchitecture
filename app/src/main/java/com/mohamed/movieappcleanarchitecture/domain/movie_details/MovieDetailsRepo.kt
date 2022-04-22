package com.mohamed.movieappcleanarchitecture.domain.movie_details

import com.mohamed.movieappcleanarchitecture.domain.movie_details.entity.MovieDetailsResponse

interface MovieDetailsRepo {
    suspend fun getMoveByID(id: Int):MovieDetailsResponse
}