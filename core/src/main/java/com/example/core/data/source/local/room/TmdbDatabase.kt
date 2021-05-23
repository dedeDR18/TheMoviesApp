package com.example.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.core.data.source.local.entity.GenreEntity
import com.example.core.data.source.local.entity.MovieEntity

/**
 * Created on : 21/05/21 | 00.05
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
@Database(entities = [GenreEntity::class, MovieEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TmdbDatabase : RoomDatabase() {
    abstract fun tmdbDao(): TmdbDao
}