package com.mohamed.movieappcleanarchitecture.domain.movie_details.entity

class MovieDetailsResponse(

    val title: String? = null,
    val release_date: String? = null,
    val original_language: String? = null,
    val poster_path: String? = null,
    val vote_count: String? = null,
    val video: Boolean = false,
    val id: Int,
    val vote_average:Float=0f
)
