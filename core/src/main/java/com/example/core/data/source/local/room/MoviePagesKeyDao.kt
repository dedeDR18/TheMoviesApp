package com.example.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.data.source.local.entity.MoviePagesKey
import kotlinx.coroutines.flow.Flow

/**
 * Created on : 24/05/21 | 11.57
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
@Dao
interface MoviePagesKeyDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun saveMoviePageKeys(redditKey: MoviePagesKey)
//
//    @Query("SELECT * FROM tmdbpageskey ORDER BY id DESC")
//    suspend fun getMoviePagesKey(): List<MoviePagesKey>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMoviePageKeys(moviePagesKey: List<MoviePagesKey>)

    @Query("SELECT * FROM tmdbpageskey WHERE id = :id ORDER BY currentPage DESC LIMIT 1")
    fun getMoviePagesKey(id: Int): Flow<MoviePagesKey>

    @Query("DELETE FROM tmdbpageskey")
    suspend fun clearMoviePagesKey()
}