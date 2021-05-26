package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/**
 * Created on : 26/05/21 | 02.16
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

@Parcelize
data class Review(
    val author: String,
    val authorDetails: AuthorDetails,
    val content: String,
    val createdAt: String,
    val id: String,
    val updatedAt: String,
    val url: String
) : Parcelable