package com.example.core.utils

import com.example.core.data.source.local.entity.GenreEntity
import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.remote.response.GenreItem
import com.example.core.data.source.remote.response.MovieResponse
import com.example.core.domain.model.Genre
import com.example.core.domain.model.Movie

/**
 * Created on : 21/05/21 | 12.54
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
object DataMapper {
    fun mapGenreResponseToGenreEntity(input: List<GenreItem>): List<GenreEntity> {
        val arrayList = ArrayList<GenreEntity>()
        input.map { input ->
            val g = GenreEntity(
                id = input.id,
                name = input.name
            )
            arrayList.add(g)
        }
        return arrayList
    }

    fun mapGenreEntityToGenreDomain(input: List<GenreEntity>): List<Genre> {
        val arrayList = ArrayList<Genre>()
        input.map { input ->
            val g = Genre(
                id = input.id,
                name = input.name
            )
            arrayList.add(g)
        }
        return arrayList
    }

    fun mapMovieResponseToMovieEntity(input: List<MovieResponse>): List<MovieEntity> {
        val arrayList = ArrayList<MovieEntity>()
        input.map { input ->
            val m = MovieEntity(
                id = input.id,
                adult = input.adult,
                backdrop_path = input.backdrop_path,
                genre_ids = input.genre_ids,
                original_language = input.original_language,
                original_title = input.original_title,
                overview = input.overview,
                popularity = input.popularity,
                poster_path = input.poster_path,
                release_date = input.release_date,
                title = input.title,
                video = input.video,
                vote_average = input.vote_average,
                vote_count = input.vote_count
            )
            arrayList.add(m)
        }
        return arrayList
    }

    fun mapMovieEntityToMovieDomain(input: List<MovieEntity>): List<Movie> {
        val arrayList = ArrayList<Movie>()
        input.map { input ->
            val m = Movie(
                id = input.id,
                adult = input.adult,
                genre_ids = input.genre_ids,
                original_language = input.original_language,
                original_title = input.original_title,
                overview = input.overview,
                popularity = input.popularity,
                poster_path = input.poster_path,
                release_date = input.release_date,
                title = input.title,
                vote_average = input.vote_average,
                vote_count = input.vote_count
            )
            arrayList.add(m)
        }
        return arrayList
    }
}