package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

/**
 * Created on : 26/05/21 | 02.17
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

@Parcelize
data class AuthorDetails(
    val avatarPath: @RawValue Any?,
    val name: String,
    val rating: Int?,
    val username: String
) : Parcelable