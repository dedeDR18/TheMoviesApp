package com.example.themoviesapp.ui.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.TmdbUsecase

/**
 * Created on : 26/05/21 | 15.36
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

class ReviewViewModel (private val usecase: TmdbUsecase) : ViewModel(){
    fun fetchReview(movieId: Int, page: Int) =
        usecase.fetchMovieReview(movieId, page).asLiveData()

    fun lastestPageExecute(movieId: Int) = usecase.getReviewCurrentPage(movieId).asLiveData()
}