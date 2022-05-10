package com.example.rickandmoryapiwithroom.service

import com.example.rickandmoryapiwithroom.model.Character
import com.example.rickandmoryapiwithroom.model.ListCharacters

class ServiceImpl() : Service {

    private val api by lazy {
        ServiceLocator.getInstanceApi()
    }

    override suspend fun loadAllCharacters(page: Int): ListCharacters {
        return api.getAllCharacters(page)
    }

    override suspend fun loadCharacter(id: Int): Character {
        return api.getCharacter(id)
    }
}