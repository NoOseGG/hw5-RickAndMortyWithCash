package com.example.rickandmortywithcash.koin

import com.example.rickandmortywithcash.Constants
import com.example.rickandmortywithcash.retrofit.RickAndMortyApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        OkHttpClient.Builder().build()
    }

    single<RickAndMortyApi> {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(RickAndMortyApi::class.java)
    }
}