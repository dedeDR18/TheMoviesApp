package com.example.core.utils

import com.example.core.data.source.local.entity.GenreEntity
import com.example.core.data.source.remote.response.GenreItem
import com.example.core.domain.model.Genre

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

    fun mapGenreEntityToGenreDomain(input: List<GenreEntity>) : List<Genre>{
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
}