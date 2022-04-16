package com.mohamed.movieappcleanarchitecture.data.main_movies

import com.mohamed.movieappcleanarchitecture.data.common.module.NetworkModule
import com.mohamed.movieappcleanarchitecture.data.main_movies.remote.api.MoviesApi
import com.mohamed.movieappcleanarchitecture.data.main_movies.repository.MoviesRepositoryImp
import com.mohamed.movieappcleanarchitecture.domain.main_movies.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object MoviesModule {


    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(moviesApi: MoviesApi): MoviesRepository {
        return MoviesRepositoryImp(moviesApi)
    }
}