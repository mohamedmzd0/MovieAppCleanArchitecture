package com.mohamed.movieappcleanarchitecture.domain.main_movies.entity

import com.mohamed.movieappcleanarchitecture.domain.movie_details.entity.MovieDetailsResponse

data class MoivesResponse(
    val page: Int,
    val results: ArrayList<MovieDetailsResponse>
)

