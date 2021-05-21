package com.example.core.domain.usecase

import com.example.core.domain.model.Genre
import com.example.core.domain.repository.ITmdbRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

/**
 * Created on : 21/05/21 | 12.30
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

class TmdbInteractor (private val repository: ITmdbRepository) : TmdbUsecase{
    override fun fetchGenre(coroutineScope: CoroutineScope) = repository.fetchGenre(coroutineScope)

    override fun getGenre(): Flow<List<Genre>> = repository.getGenre()

}