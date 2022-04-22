package com.mohamed.movieappcleanarchitecture.data.movie_details

import com.mohamed.movieappcleanarchitecture.data.common.module.NetworkModule
import com.mohamed.movieappcleanarchitecture.data.movie_details.repository.MovieDetailsRepositoryImp
import com.mohamed.movieappcleanarchitecture.data.movie_details.remote.api.MovieDetailsApi
import com.mohamed.movieappcleanarchitecture.domain.movie_details.MovieDetailsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object MovieDetailsModule {


    @Provides
    fun provideMovieDetailsRepo(movieDetailsApi: MovieDetailsApi): MovieDetailsRepo {
        return MovieDetailsRepositoryImp(movieDetailsApi)
    }

    @Provides
    fun provideMovieDetailsApi(retrofit: Retrofit): MovieDetailsApi {
        return retrofit.create(MovieDetailsApi::class.java)
    }

}