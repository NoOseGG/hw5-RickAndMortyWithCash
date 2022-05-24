package com.example.rickandmortywithcash.koin

import androidx.room.Room
import com.example.rickandmortywithcash.room.AppDataBase
import com.example.rickandmortywithcash.service.CharacterRepository
import com.example.rickandmortywithcash.service.Service
import com.example.rickandmortywithcash.service.ServiceImpl
import org.koin.dsl.module

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