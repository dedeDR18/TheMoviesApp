package com.example.core.data.source

import android.util.Log
import com.example.core.data.source.local.room.TmdbDao
import com.example.core.data.source.remote.network.TmdbService
import com.example.core.domain.model.Genre
import com.example.core.domain.repository.ITmdbRepository
import com.example.core.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import java.lang.Exception

/**
 * Created on : 21/05/21 | 01.32
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

class TmdbRepository(private val apiService: TmdbService, val tmdbDao: TmdbDao) : ITmdbRepository {
    override fun getGenre(): Flow<List<Genre>> = flow {
        tmdbDao.getAllGenre().collect { listGenreEntity ->
            emit(DataMapper.mapGenreEntityToGenreDomain(listGenreEntity))
        }
    }

    override fun fetchGenre(coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            try {
                val response = apiService.fetchGenre().awaitResponse()
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.genres.let { list ->
                        list?.let {
                            tmdbDao.insertGenre(DataMapper.mapGenreResponseToGenreEntity(list))
                        }
                    }
                } else {
                    Log.d("TAG", "gagal execute Api...")
                }
            } catch (e: Exception) {
                Log.d("TAG", "error = ${e.message}")
            }
        }
    }

}