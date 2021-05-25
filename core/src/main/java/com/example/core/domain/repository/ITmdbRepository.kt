package com.example.core.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.core.data.Resource
import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.local.entity.MoviePagesKey
import com.example.core.domain.model.Genre
import com.example.core.domain.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

/**
 * Created on : 21/05/21 | 12.28
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
interface ITmdbRepository {
    fun getGenre(): Flow<List<Genre>>
    fun fetchGenre(coroutineScope: CoroutineScope)
    fun fetchMovieByGenre(genreId: Int, page: Int): Flow<Resource<List<Movie>>>
    fun getMovieByGenre(genreId: Int): Flow<List<Movie>>
    fun getCurrentPage(genreId: Int): Flow<MoviePagesKey>


    //paging 3
    //fun getMovieByGenrePagingFlow(): Flow<PagingData<MovieEntity>>
}