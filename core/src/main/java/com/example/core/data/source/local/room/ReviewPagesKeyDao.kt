package com.example.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.data.source.local.entity.ReviewPagesKey
import kotlinx.coroutines.flow.Flow

/**
 * Created on : 26/05/21 | 14.00
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
@Dao
interface ReviewPagesKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveReviewPageKeys(reviewPagesKey: List<ReviewPagesKey>)

    @Query("SELECT * FROM tmdbreviewpageskey WHERE id = :id ORDER BY currentPage DESC LIMIT 1")
    fun getReviewPagesKey(id: Int): Flow<ReviewPagesKey>

    @Query("DELETE FROM tmdbreviewpageskey")
    suspend fun clearReviewPagesKey()
}