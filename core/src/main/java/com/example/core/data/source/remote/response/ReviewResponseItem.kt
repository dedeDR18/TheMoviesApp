package com.example.core.data.source.remote.response

/**
 * Created on : 26/05/21 | 01.59
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
data class ReviewResponseItem(
    val author: String,
    val author_details: AuthorDetailsResponse,
    val content: String,
    val created_at: String,
    val id: String,
    val updated_at: String,
    val url: String
)