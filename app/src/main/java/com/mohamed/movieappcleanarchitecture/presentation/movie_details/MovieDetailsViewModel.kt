package com.mohamed.movieappcleanarchitecture.presentation.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamed.movieappcleanarchitecture.domain.movie_details.entity.MovieDetailsResponse
import com.mohamed.movieappcleanarchitecture.domain.movie_details.use_case.MovieDetailsUseCase
import com.mohamed.movieappcleanarchitecture.infrastructure.utils.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val movieDetailsUseCase: MovieDetailsUseCase) :
    ViewModel() {


    fun getMovie(id: Int)= flow {
            emit(Resources.OnLoading())
            kotlin.runCatching {
                movieDetailsUseCase.getMovie(id)
            }.onSuccess {
                emit(Resources.OnSuccess(it))
            }.onFailure {
                emit(Resources.OnError<MovieDetailsResponse>(it))
            }
        }
    }
