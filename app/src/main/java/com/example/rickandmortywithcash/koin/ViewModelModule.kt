package com.example.rickandmortywithcash.koin

import com.example.rickandmortywithcash.screens.viewmodel.ViewModelList
import com.example.rickandmortywithcash.service.ServiceImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        ViewModelList(
            service = get()
        )
    }
}