package com.example.rickandmortywithcash.screens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmortywithcash.paging.CharacterDataSource
import com.example.rickandmortywithcash.service.ServiceImpl

class ViewModelList(
    private val service: ServiceImpl
) : ViewModel() {

    val characters = Pager(PagingConfig(pageSize = 20)) {
        CharacterDataSource(service)
    }.flow.cachedIn(viewModelScope)

}