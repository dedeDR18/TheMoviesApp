package com.example.themoviesapp.ui.listmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.TmdbUsecase

/**
 * Created on : 22/05/21 | 17.32
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

class MoviesViewModel(private val usecase: TmdbUsecase): ViewModel(){

    fun fetchMoviesByGenre(genreId: Int){
        usecase.fetchMovieByGenre(genreId, 1, viewModelScope)
    }

    fun movies(genreId: Int) = usecase.getMovieByGenre(genreId).asLiveData()
}