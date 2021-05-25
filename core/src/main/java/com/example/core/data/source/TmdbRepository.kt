package com.example.core.data.source

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.data.Resource
import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.local.entity.MoviePagesKey
import com.example.core.data.source.local.room.TmdbDao
import com.example.core.data.source.local.room.TmdbDatabase
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.TmdbService
import com.example.core.data.source.remote.response.MovieResponse
import com.example.core.domain.model.Genre
import com.example.core.domain.model.Movie
import com.example.core.domain.model.Review
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

class TmdbRepository(
    private val apiService: TmdbService,
    val tmdbDatabase: TmdbDatabase
) : ITmdbRepository {
    override fun getGenre(): Flow<List<Genre>> = flow {
        tmdbDatabase.tmdbDao().getAllGenre().collect { listGenreEntity ->
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
                            tmdbDatabase.tmdbDao()
                                .insertGenre(DataMapper.mapGenreResponseToGenreEntity(list))
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

    override fun fetchMovieByGenre(genreId: Int, page: Int) = flow<Resource<List<Movie>>> {
        emit(Resource.Loading())
        try {
            val response = apiService.fetchMovieListByGenre(genreId, page).awaitResponse()
            if (response.isSuccessful) {
                val data = response.body()
                data?.let {
                    val listMovie = it.results
                    val currentPage = it.page
                    val totalPage = it.total_pages
                    tmdbDatabase.tmdbPagesKeyDao().saveMoviePageKeys(
                        listOf(
                            MoviePagesKey(
                                genreId,
                                currentPage,
                                totalPage
                            )
                        )
                    )
                    emit(Resource.Success(DataMapper.mapMovieResponseToMovieDomain(listMovie)))
                }

            } else {
                Log.d("TAG", "Error Api..")
                emit(Resource.Error("Error Api.."))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error ${e.message}"))
        }
    }

    override fun getMovieByGenre(genreId: Int): Flow<List<Movie>> = flow {
        tmdbDatabase.tmdbDao().getMovieByGenre(genreId).collect { listMovieByGenre ->
            emit(DataMapper.mapMovieEntityToMovieDomain(listMovieByGenre))
        }
    }

    override fun getCurrentPage(genreId: Int): Flow<MoviePagesKey> = flow {
        tmdbDatabase.tmdbPagesKeyDao().getMoviePagesKey(genreId).collect {
            emit(it)
        }
    }

    override fun fetchMovieReview(movieId: Int, page: Int) = flow<Resource<List<Review>>> {
        emit(Resource.Loading())
        try {
            val response = apiService.fetchMovieReview(movieId, page).awaitResponse()
            if (response.isSuccessful){
                val data = response.body()
                data?.let {
                    val listReview = it.results
                    emit(Resource.Success(DataMapper.mapReviewResponseToReviewDomain(listReview)))
                }
            } else {
                Log.d("TAG", "Error Api..")
                emit(Resource.Error("Error Api.."))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error ${e.message}"))
        }
    }
}