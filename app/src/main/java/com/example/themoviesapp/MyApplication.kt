package com.example.themoviesapp

import android.app.Application
import com.example.core.di.databaseModule
import com.example.core.di.networkModule
import com.example.core.di.repositoryModule
import com.example.themoviesapp.di.useCaseModule
import com.example.themoviesapp.di.viewmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created on : 20/05/21 | 23.53
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
            startKoin {
                androidLogger(Level.NONE)
                androidContext(this@MyApplication)
                modules(
                    listOf(
                        databaseModule,
                        networkModule,
                        repositoryModule,
                        useCaseModule,
                        viewmodelModule
                    )
                )
            }
    }
}