package com.example.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created on : 26/05/21 | 13.57
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

@Entity(tableName = "tmdbreviewpageskey")
data class ReviewPagesKey(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val currentPage: Int,
    val totalPage: Int,
    val totalResult: Int,
    val updatedAt: Long = System.currentTimeMillis()
)