package com.example.core.domain.usecase

import androidx.paging.PagingData
import com.example.core.data.Resource
import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.local.entity.MoviePagesKey
import com.example.core.domain.model.Genre
import com.example.core.domain.model.Movie
import com.example.core.domain.repository.ITmdbRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

/**
 * Created on : 21/05/21 | 12.30
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

class TmdbInteractor(private val repository: ITmdbRepository) : TmdbUsecase {
    override fun fetchGenre(coroutineScope: CoroutineScope) = repository.fetchGenre(coroutineScope)

    override fun getGenre(): Flow<List<Genre>> = repository.getGenre()
    override fun fetchMovieByGenre(genreId: Int, page: Int): Flow<Resource<List<Movie>>> =
        repository.fetchMovieByGenre(genreId, page)

    override fun getMovieByGenre(genreId: Int): Flow<List<Movie>> =
        repository.getMovieByGenre(genreId)

    override fun getCurrentPage(genreId: Int): Flow<MoviePagesKey> =
        repository.getCurrentPage(genreId)

    //paging 3
    //override fun getMovieByGenrePagingFlow(): Flow<PagingData<MovieEntity>> = repository.getMovieByGenrePagingFlow()

}