package com.example.themoviesapp.ui.listmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.domain.usecase.TmdbUsecase
import kotlinx.coroutines.flow.Flow

/**
 * Created on : 22/05/21 | 17.32
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

class MoviesViewModel(private val usecase: TmdbUsecase) : ViewModel() {

    fun fetchMoviesByGenre(genreId: Int, page: Int) =
        usecase.fetchMovieByGenre(genreId, page).asLiveData()

    fun lastestPageExecute(genreId: Int) = usecase.getCurrentPage(genreId).asLiveData()

}