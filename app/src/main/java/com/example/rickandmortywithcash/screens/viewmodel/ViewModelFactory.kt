package com.example.rickandmortywithcash.screens.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortywithcash.service.ServiceImpl
import com.example.rickandmortywithcash.service.ServiceLocator

class ViewModelFactory() : ViewModelProvider.Factory {

    private val service: ServiceImpl by lazy {
        ServiceLocator.getInstanceService()
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelList(
            service = service,
        ) as T
    }
}