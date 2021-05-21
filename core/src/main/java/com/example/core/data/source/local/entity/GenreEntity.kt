package com.example.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

/**
 * Created on : 21/05/21 | 00.14
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

@Entity(tableName = "genreentities")
data class GenreEntity(
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String
)