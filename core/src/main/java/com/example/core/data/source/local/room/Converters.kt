package com.example.core.data.source.local.room

import androidx.room.TypeConverter

/**
 * Created on : 24/05/21 | 01.01
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
class Converters {
    @TypeConverter
    fun toList(genreIds: String): List<Int> {
        val list = mutableListOf<Int>()

        val array = genreIds.split(",".toRegex()).dropLastWhile {
            it.isEmpty()
        }.toTypedArray()

        for (s in array) {
            if (s.isNotEmpty()) {
                list.add(s.toInt())
            }
        }
        return list
    }

    @TypeConverter
    fun fromList(list: List<Int>): String {
        var genreIds=""
        for (i in list) genreIds += ",$i"
        return genreIds
    }
}