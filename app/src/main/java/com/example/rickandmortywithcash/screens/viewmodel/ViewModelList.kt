package com.example.rickandmortywithcash.screens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmortywithcash.paging.CharacterDataSource
import com.example.rickandmortywithcash.service.Service
import com.example.rickandmortywithcash.service.ServiceImpl
import org.koin.core.component.KoinComponent

class ViewModelList(
    private val service: Service
) : ViewModel(){

    val characters = Pager(PagingConfig(PAGE_SIZE)) {
        CharacterDataSource(service)
    }.flow.cachedIn(viewModelScope)

    companion object {
        const val PAGE_SIZE = 15
    }
}