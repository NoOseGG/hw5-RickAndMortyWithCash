package com.example.rickandmoryapiwithroom.screens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmoryapiwithroom.service.ServiceImpl
import com.example.rickandmoryapiwithroom.service.ServiceLocator

class ViewModelFactory : ViewModelProvider.Factory {

    private val service: ServiceImpl by lazy {
        ServiceLocator.getInstanceService()
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelList(
            service = service
        ) as T
    }
}