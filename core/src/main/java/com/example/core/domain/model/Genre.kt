package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created on : 21/05/21 | 01.43
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

@Parcelize
data class Genre(
    val id: Int,
    val name: String
): Parcelable