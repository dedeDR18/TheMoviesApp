package com.example.core.utils

import com.example.core.data.source.local.entity.GenreEntity
import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.remote.response.AuthorDetailsResponse
import com.example.core.data.source.remote.response.GenreItem
import com.example.core.data.source.remote.response.MovieResponse
import com.example.core.data.source.remote.response.ReviewResponseItem
import com.example.core.domain.model.AuthorDetails
import com.example.core.domain.model.Genre
import com.example.core.domain.model.Movie
import com.example.core.domain.model.Review

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
                backdropPath = input.backdrop_path,
                genreIds = input.genre_ids,
                originalLanguage = input.original_language,
                originalTitle = input.original_title,
                overview = input.overview,
                popularity = input.popularity,
                posterPath = input.poster_path,
                releaseDate = input.release_date,
                title = input.title,
                video = input.video,
                voteAverage = input.vote_average,
                voteCount = input.vote_count
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
                genreIds = input.genreIds,
                originalLanguage = input.originalLanguage,
                originalTitle = input.originalTitle,
                overview = input.overview,
                popularity = input.popularity,
                posterPath = input.posterPath,
                releaseDate = input.releaseDate,
                title = input.title,
                voteAverage = input.voteAverage,
                voteCount = input.voteCount
            )
            arrayList.add(m)
        }
        return arrayList
    }

    fun mapMovieResponseToMovieDomain(input: List<MovieResponse>): List<Movie> {
        val arrayList = ArrayList<Movie>()
        input.map { input ->
            val m = Movie(
                id = input.id,
                adult = input.adult,
                genreIds = input.genre_ids,
                originalLanguage = input.original_language,
                originalTitle = input.original_title,
                overview = input.overview,
                popularity = input.popularity,
                posterPath = input.poster_path,
                releaseDate = input.release_date,
                title = input.title,
                voteAverage = input.vote_average,
                voteCount = input.vote_count
            )
            arrayList.add(m)
        }
        return arrayList
    }

    fun mapReviewResponseToReviewDomain(input: List<ReviewResponseItem?>): List<Review> {
        val arrayList = ArrayList<Review>()
        input.map { data ->
            data?.let { input ->
                val r = Review(
                    author = input.author,
                    authorDetails = mapAuthorDetailsResponseToAuthorDetailsDomain(input = input.author_details),
                    content = input.content,
                    createdAt = input.created_at,
                    id = input.id,
                    updatedAt = input.updated_at,
                    url = input.url
                )
                arrayList.add(r)
            }
        }
        return arrayList
    }

    fun mapAuthorDetailsResponseToAuthorDetailsDomain(input: AuthorDetailsResponse) = AuthorDetails(
        avatarPath = input.avatar_path,
        name = input.name,
        rating = input.rating,
        username = input.username
    )
}