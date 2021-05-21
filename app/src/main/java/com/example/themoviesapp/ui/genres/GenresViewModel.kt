package com.example.themoviesapp.ui.genres

import androidx.lifecycle.*
import com.example.core.domain.model.Genre
import com.example.core.domain.usecase.TmdbUsecase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created on : 21/05/21 | 13.15
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

class GenresViewModel(private val usecase: TmdbUsecase) : ViewModel(){

    val genres = usecase.getGenre().asLiveData()

}