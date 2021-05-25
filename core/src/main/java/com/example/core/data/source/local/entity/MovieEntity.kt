package com.example.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created on : 22/05/21 | 01.26
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

@Entity(tableName = "movieentities")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "adult")
    val adult: Boolean,
    @ColumnInfo(name = "backdropPath")
    val backdropPath: String?,
    @ColumnInfo(name = "genreIds")
    val genreIds: List<Int>,
    @ColumnInfo(name = "originalLanguage")
    val originalLanguage: String,
    @ColumnInfo(name = "originalTitle")
    val originalTitle: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "popularity")
    val popularity: Double,
    @ColumnInfo(name = "posterPath")
    val posterPath: String,
    @ColumnInfo(name = "releaseDate")
    val releaseDate: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "video")
    val video: Boolean,
    @ColumnInfo(name = "voteAverage")
    val voteAverage: Double,
    @ColumnInfo(name = "voteCount")
    val voteCount: Int,
    @ColumnInfo(name = "updateAt")
    val updatedAt: Long = System.currentTimeMillis()
)