package com.example.rickandmortywithcash.service

import android.content.Context
import androidx.room.Room
import com.example.rickandmortywithcash.retrofit.RickAndMortyApi
import com.example.rickandmortywithcash.room.AppDataBase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ServiceLocator {

    private const val baseUrl = "https://rickandmortyapi.com/api/"
    private var api: RickAndMortyApi? = null
    private var service: ServiceImpl? = null
    private lateinit var applicationContext: Context
    private val dataBase by lazy {
        Room.databaseBuilder(applicationContext, AppDataBase::class.java, "rickandmorty.db")
            .build()
    }

    val characterRepository: CharacterRepository by lazy {
        CharacterRepository(dataBase.characterDao())
    }

    fun init(context: Context) {
        applicationContext = context
    }

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
        if (api == null) {
            api = provideRickAndMortyApi()
        }
        return api as RickAndMortyApi
    }

    fun getInstanceService(): ServiceImpl {
        if (service == null) {
            service = provideService()
        }
        return service as ServiceImpl
    }
}