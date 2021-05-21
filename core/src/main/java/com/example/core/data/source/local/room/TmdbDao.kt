package com.example.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.data.source.local.entity.GenreEntity
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
}