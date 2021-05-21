package com.example.core.data.source.remote.network

import com.example.core.BuildConfig
import com.example.core.data.source.remote.response.GenreItem
import com.example.core.data.source.remote.response.TmdbGenreResponse
import retrofit2.Call
import retrofit2.http.GET


/**
 * Created on : 21/05/21 | 00.44
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
interface TmdbService {

    @GET("genre/movie/list?api_key=${BuildConfig.API_KEY}&language=en-US")
    fun fetchGenre(): Call<TmdbGenreResponse>
}