package com.example.themoviesapp.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.TmdbUsecase

/**
 * Created on : 21/05/21 | 21.49
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

class SplashViewModel (private val usecase: TmdbUsecase) : ViewModel(){

    fun fetchGenre(){
        usecase.fetchGenre(viewModelScope)
    }
}