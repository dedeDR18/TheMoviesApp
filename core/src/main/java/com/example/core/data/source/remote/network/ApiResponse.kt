package com.example.core.data.source.remote.network

/**
 * Created on : 28/04/21 | 06.39
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

sealed class ApiResponse<out R> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val errorMessage: String) : ApiResponse<Nothing>()
    object Empty : ApiResponse<Nothing>()
}