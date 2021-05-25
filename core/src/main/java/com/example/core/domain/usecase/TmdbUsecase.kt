package com.example.core.domain.usecase

import androidx.paging.PagingData
import com.example.core.data.Resource
import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.local.entity.MoviePagesKey
import com.example.core.domain.model.Genre
import com.example.core.domain.model.Movie
import com.example.core.domain.model.Review
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
    fun fetchMovieByGenre(genreId: Int, page: Int): Flow<Resource<List<Movie>>>
    fun getMovieByGenre(genreId: Int): Flow<List<Movie>>
    fun getCurrentPage(genreId: Int): Flow<MoviePagesKey>
    fun fetchMovieReview(movieId: Int, page: Int): Flow<Resource<List<Review>>>

    //paging 3
    //fun getMovieByGenrePagingFlow(): Flow<PagingData<MovieEntity>>
}