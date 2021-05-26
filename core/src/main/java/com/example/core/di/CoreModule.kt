package com.example.core.di

import androidx.room.Room
import com.example.core.data.source.TmdbRepository
import com.example.core.data.source.local.room.TmdbDatabase
import com.example.core.data.source.remote.network.TmdbService
import com.example.core.domain.repository.ITmdbRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created on : 21/05/21 | 00.24
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

val databaseModule = module {
    factory { get<TmdbDatabase>().tmdbDao() }
    factory { get<TmdbDatabase>().tmdbPagesKeyDao() }
    factory { get<TmdbDatabase>().tmdbReviewPagesKeyDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            TmdbDatabase::class.java,
            "tmdbdatabase.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}

val networkModule = module {
    single { createOkHttpClient() }
    single { createRetrofit(get()) }
}

val repositoryModule = module {
    single<ITmdbRepository> { TmdbRepository(get(), get()) }
}

private fun createRetrofit(okHttpClient: OkHttpClient): TmdbService {
    return Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(TmdbService::class.java)
}

private fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .build()
}

