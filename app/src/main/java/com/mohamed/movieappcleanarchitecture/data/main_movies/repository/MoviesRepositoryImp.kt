package com.mohamed.movieappcleanarchitecture.data.main_movies.repository

import com.mohamed.movieappcleanarchitecture.data.main_movies.remote.api.MoviesApi
import com.mohamed.movieappcleanarchitecture.domain.main_movies.MoviesRepository
import com.mohamed.movieappcleanarchitecture.domain.main_movies.entity.MoivesResponse
import com.mohamed.movieappcleanarchitecture.infrastructure.utils.Constants
import com.mohamed.movieappcleanarchitecture.infrastructure.utils.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class MoviesRepositoryImp @Inject constructor(val moviesApi: MoviesApi) : MoviesRepository {

/*    override suspend fun getMovies(): Flow<Resources<MoivesResponse>> {
        return flow {
            emit(Resources.OnLoading<MoivesResponse>())
            kotlin.runCatching {
                moviesApi.getMovies(Constants.API_KEY)
            }.onSuccess {
                emit(Resources.OnSuccess(_data = it))
            }.onFailure {
                emit(Resources.OnError<MoivesResponse>(throwable = it))
            }
        }
    }*/

    override suspend fun getMovies(page: Int): MoivesResponse {
        return moviesApi.getMovies(Constants.API_KEY,page)
    }
}