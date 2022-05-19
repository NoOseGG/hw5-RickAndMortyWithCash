package com.example.rickandmortywithcash.service

import com.example.rickandmortywithcash.model.ListCharacters
import com.example.rickandmortywithcash.model.Character
import com.example.rickandmortywithcash.model.CharacterDetails
import com.example.rickandmortywithcash.retrofit.RickAndMortyApi

class ServiceImpl(
    private val api: RickAndMortyApi,
    private val characterRepository: CharacterRepository,
) : Service {

    override suspend fun loadAllCharacters(page: Int): ListCharacters {
        return api.getAllCharacters(page)
    }

    override suspend fun loadCharacter(id: Int): CharacterDetails {
        return api.getCharacter(id)
    }

    override suspend fun loadAllCharactersFromDb(): List<Character> {
        return characterRepository.loadAllCharactersFromDb()
    }

    override suspend fun insertCharacterToDb(character: Character) {
        characterRepository.insertCharacterToDb(character)
    }
}