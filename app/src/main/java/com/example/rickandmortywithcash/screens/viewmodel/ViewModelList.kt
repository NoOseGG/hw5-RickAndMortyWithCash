package com.example.rickandmortywithcash.screens.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.rickandmortywithcash.model.Character
import com.example.rickandmortywithcash.networkChanges
import com.example.rickandmortywithcash.service.ServiceImpl
import kotlinx.coroutines.flow.first

class ViewModelList(
    private val context: Context,
    private val service: ServiceImpl
) : ViewModel() {

    private val _charactersList = MutableStateFlow(mutableListOf<Character>())
    val charactersList: StateFlow<MutableList<Character>> = _charactersList.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    private var pageQuntity = 1

    suspend fun loadAllCharacters(page: Int) {
        if (context.networkChanges.first()) {
            loadAllCharacterFromOnline(page)
        } else {
            loadAllCharactersFromDb()
        }
    }

    suspend fun loadAllCharacterFromOnline(page: Int) {
        if (page <= pageQuntity) {
            _isLoading.value = true
            val listCharacters = service.loadAllCharacters(page)
            charactersList.value.addAll(listCharacters.results)
            insertCharacterToDb(listCharacters.results)
            _isLoading.value = false
            pageQuntity = listCharacters.info.pages
        }
    }

    suspend fun loadAllCharactersFromDb() {
        val list = service.loadAllCharactersFromDb()
        charactersList.value.clear()
        charactersList.value.addAll(list)
        println(charactersList.value)
    }

    suspend fun insertCharacterToDb(list: List<Character>) {
        for (character in list) {
            service.insertCharacterToDb(character)
        }
    }
}