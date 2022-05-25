package com.example.rickandmortywithcash.koin

import com.example.rickandmortywithcash.screens.viewmodel.ViewModelDetailsFragment
import com.example.rickandmortywithcash.screens.viewmodel.ViewModelEpisodeDetails
import com.example.rickandmortywithcash.screens.viewmodel.ViewModelList
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {

    viewModelOf(::ViewModelList)
    viewModelOf(::ViewModelEpisodeDetails)
    viewModelOf(::ViewModelDetailsFragment)
}