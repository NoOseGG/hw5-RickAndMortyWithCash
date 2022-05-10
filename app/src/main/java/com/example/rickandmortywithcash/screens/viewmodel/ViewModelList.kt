package com.example.rickandmoryapiwithroom.screens.viewmodel

import androidx.lifecycle.ViewModel
import com.example.rickandmoryapiwithroom.model.Character
import com.example.rickandmoryapiwithroom.service.ServiceImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ViewModelList(
    private val service: ServiceImpl
    ) : ViewModel() {

    private val _charactersList = MutableStateFlow(mutableListOf<Character>())
    val charactersList: StateFlow<MutableList<Character>> = _charactersList.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    private var pageQuntity = 1

    suspend fun loadAllCharacters(page: Int) {
        if(page <= pageQuntity) {
            _isLoading.value = true
            val listCharacters = service.loadAllCharacters(page)
            charactersList.value.addAll(listCharacters.results)
            println(charactersList.value)
            _isLoading.value = false
            pageQuntity = listCharacters.info.pages
            println("page = $page , pg = $pageQuntity")
        }
    }
}