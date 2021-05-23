package com.example.core.data.source.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.data.source.local.entity.GenreEntity
import com.example.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created on : 21/05/21 | 00.05
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
@Dao
interface TmdbDao{

    @Query("SELECT * FROM genreentities")
    fun getAllGenre(): Flow<List<GenreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(listGenre: List<GenreEntity>)

    @Query("SELECT * FROM movieentities WHERE genre_ids LIKE '%' || :genreId || '%'")
    fun getMovieByGenre(genreId: Int): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(listMovies: List<MovieEntity>)
}