package com.example.core.data.source.remote.response

/**
 * Created on : 26/05/21 | 01.58
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

data class TmdbReviewResponse(
    val id:Int,
    val page: Int,
    val results: List<ReviewResponseItem?>,
    val total_pages: Int,
    val total_results: Int
)