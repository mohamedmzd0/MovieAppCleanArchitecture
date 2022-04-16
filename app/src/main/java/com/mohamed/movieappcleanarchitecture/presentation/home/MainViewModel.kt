package com.mohamed.movieappcleanarchitecture.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamed.movieappcleanarchitecture.domain.main_movies.entity.MoivesResponse
import com.mohamed.movieappcleanarchitecture.domain.main_movies.use_case.MoviesUseCase
import com.mohamed.movieappcleanarchitecture.infrastructure.utils.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val moviesUseCase: MoviesUseCase) : ViewModel() {
     val state = MutableStateFlow<Resources<MoivesResponse>>(Resources.OnLoading())


    fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            moviesUseCase.getMovies().onStart {
                state.value = Resources.OnLoading()
            }.catch {
                state.value = Resources.OnError(it)
            }.collect {
                state.value = it
            }
        }
    }

}