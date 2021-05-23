package com.example.core.domain.usecase

import com.example.core.domain.model.Genre
import com.example.core.domain.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow


/**
 * Created on : 21/05/21 | 12.29
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

interface TmdbUsecase{
    fun fetchGenre(coroutineScope: CoroutineScope)
    fun getGenre(): Flow<List<Genre>>
    fun fetchMovieByGenre(genreId: Int, page: Int, coroutineScope: CoroutineScope)
    fun getMovieByGenre(genreId: Int): Flow<List<Movie>>
}