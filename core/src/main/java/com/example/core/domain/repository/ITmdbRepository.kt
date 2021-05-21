package com.example.core.domain.repository

import com.example.core.domain.model.Genre
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
}