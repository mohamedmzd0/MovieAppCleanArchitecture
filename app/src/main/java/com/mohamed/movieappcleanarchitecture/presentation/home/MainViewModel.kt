package com.mohamed.movieappcleanarchitecture.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.mohamed.movieappcleanarchitecture.domain.main_movies.entity.MoivesResponse
import com.mohamed.movieappcleanarchitecture.domain.main_movies.use_case.MoviesUseCase
import com.mohamed.movieappcleanarchitecture.domain.movie_details.entity.MovieDetailsResponse
import com.mohamed.movieappcleanarchitecture.infrastructure.utils.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val moviesUseCase: MoviesUseCase) : ViewModel() {
    val movlies = MutableStateFlow<Resources<MoivesResponse>>(Resources.OnLoading())


    val movies: Flow<PagingData<MovieDetailsResponse>> = Pager(PagingConfig(pageSize = 20)) {
        MoviePagingSource(moviesUseCase)
    }.flow
        .cachedIn(viewModelScope)

/*    fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            moviesUseCase.getMovies().onStart {
                movlies.value = Resources.OnLoading()
            }.catch {
                movlies.value = Resources.OnError(it)
            }.collect {
                movlies.value = it
            }
        }
    }*/


    class MoviePagingSource(
        val movieApiService: MoviesUseCase,
    ) : PagingSource<Int, MovieDetailsResponse>() {

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDetailsResponse> {
            try {
                // Start refresh at page 1 if undefined.
                val nextPage = params.key ?: 1
                val response = movieApiService.getMovies(nextPage)

                return LoadResult.Page(
                    data = response.results,
                    prevKey = if (nextPage == 1) null else nextPage - 1,
                    nextKey = response.page + 1
                )
            } catch (e: Exception) {
                return LoadResult.Error(e)
            }
        }

        override fun getRefreshKey(state: PagingState<Int, MovieDetailsResponse>): Int? {
            return state.anchorPosition
        }
    }
}