package com.example.rickandmoryapiwithroom.service

import com.example.rickandmoryapiwithroom.retrofit.RickAndMortyApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ServiceLocator {

    private const val baseUrl = "https://rickandmortyapi.com/api/"
    private var api: RickAndMortyApi? = null
    private var service: ServiceImpl? = null

    private var characterRepository: CharacterRepository? = null

    private fun provideRickAndMortyApi(): RickAndMortyApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    private fun provideService(): ServiceImpl {
        return ServiceImpl()
    }

    fun getInstanceApi(): RickAndMortyApi {
        if(api == null) {
            api = provideRickAndMortyApi()
        }
        return api as RickAndMortyApi
    }

    fun getInstanceService(): ServiceImpl {
        if(service == null) {
            service = ServiceImpl()
        }
        return service as ServiceImpl
    }

}