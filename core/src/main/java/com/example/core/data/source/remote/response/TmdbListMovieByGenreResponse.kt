package com.example.core.data.source.remote.response

data class TmdbListMovieByGenreResponse(
    val page: Int,
    val results: List<MovieResponse>,
    val total_pages: Int,
    val total_results: Int
)