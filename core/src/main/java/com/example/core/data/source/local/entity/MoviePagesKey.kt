package com.example.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created on : 24/05/21 | 11.56
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
@Entity(tableName = "tmdbpageskey")
data class MoviePagesKey(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val currentPage: Int,
    val totalPage: Int,
    val updatedAt: Long = System.currentTimeMillis()
)