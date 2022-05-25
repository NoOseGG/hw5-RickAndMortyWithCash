package com.example.rickandmortywithcash.screens.viewmodel

import androidx.lifecycle.ViewModel
import com.example.rickandmortywithcash.model.CharacterDetails
import com.example.rickandmortywithcash.service.Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.withContext

class ViewModelDetailsFragment(
    private val service: Service
) : ViewModel() {

    val characterFlow = MutableSharedFlow<CharacterDetails>()

    suspend fun loadCharacter(id: Int) = withContext(Dispatchers.IO) {
        val character = service.loadCharacter(id)
        characterFlow.emit(character)
    }
}