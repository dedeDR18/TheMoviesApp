package com.example.core.data.source.remote.network

import com.example.core.BuildConfig
import com.example.core.data.source.remote.response.GenreItem
import com.example.core.data.source.remote.response.TmdbGenreResponse
import com.example.core.data.source.remote.response.TmdbListMovieByGenreResponse
import com.example.core.data.source.remote.response.TmdbReviewResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created on : 21/05/21 | 00.44
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
interface TmdbService {

    @GET("genre/movie/list?api_key=${BuildConfig.API_KEY}&language=en-US")
    fun fetchGenre(): Call<TmdbGenreResponse>

    @GET("discover/movie?api_key=${BuildConfig.API_KEY}")
    fun fetchMovieListByGenre(
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int
    ): Call<TmdbListMovieByGenreResponse>

    @GET("discover/movie?api_key=${BuildConfig.API_KEY}")
    fun fetchMovieListByGenreWithPaging(
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int
    ): TmdbListMovieByGenreResponse

    @GET("movie/{movieId}/reviews?api_key=${BuildConfig.API_KEY}&language=en-US")
    fun fetchMovieReview(
        @Path("movieId") movieId: Int,
        @Query("page") page: Int
    ): Call<TmdbReviewResponse>

}