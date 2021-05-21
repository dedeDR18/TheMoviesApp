package com.example.themoviesapp.di

import com.example.core.domain.usecase.TmdbInteractor
import com.example.core.domain.usecase.TmdbUsecase
import com.example.themoviesapp.ui.genres.GenresViewModel
import com.example.themoviesapp.ui.splash.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created on : 21/05/21 | 13.11
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
val useCaseModule = module {
    factory<TmdbUsecase> { TmdbInteractor(get()) }
}

val viewmodelModule = module {
    viewModel { GenresViewModel(get()) }
    viewModel { SplashViewModel(get()) }
}