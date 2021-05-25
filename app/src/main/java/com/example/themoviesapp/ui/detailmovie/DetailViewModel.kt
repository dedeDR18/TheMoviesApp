package com.example.themoviesapp.ui.detailmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.TmdbUsecase

/**
 * Created on : 26/05/21 | 01.54
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
class DetailViewModel (private val usecase: TmdbUsecase) : ViewModel(){
    fun fetchReview(movieId: Int, page: Int) =
        usecase.fetchMovieReview(movieId, page).asLiveData()
}