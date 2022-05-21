package com.example.rickandmortywithcash.koin

import androidx.room.Room
import com.example.rickandmortywithcash.Constants
import com.example.rickandmortywithcash.retrofit.RickAndMortyApi
import com.example.rickandmortywithcash.room.AppDataBase
import com.example.rickandmortywithcash.screens.viewmodel.ViewModelList
import com.example.rickandmortywithcash.service.CharacterRepository
import com.example.rickandmortywithcash.service.Repository
import com.example.rickandmortywithcash.service.Service
import com.example.rickandmortywithcash.service.ServiceImpl
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val serviceModule = module {

    single<Service> {
        ServiceImpl(
            api = get(),
            characterRepository = get()
        )
    }

    single {
        CharacterRepository(
            characterDao = get()
        )
    }

    single {
        Room.databaseBuilder(
            get(),
            AppDataBase::class.java,
            "rickandmorty.db"
        )
            .build()
    }

    single {
        get<AppDataBase>().characterDao()
    }
}