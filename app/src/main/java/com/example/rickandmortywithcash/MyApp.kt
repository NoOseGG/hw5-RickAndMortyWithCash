package com.example.rickandmortywithcash

import android.app.Application
import com.example.rickandmortywithcash.koin.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(
                networkModule,
                serviceModule,
                viewModelModule
            )
        }
    }
}