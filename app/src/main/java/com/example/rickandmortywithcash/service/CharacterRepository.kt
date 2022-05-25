package com.example.rickandmortywithcash.service

import com.example.rickandmortywithcash.model.Character

interface CharacterRepository {

    suspend fun loadAllCharactersFromDb(): List<Character>

    suspend fun insertCharacterToDb(character: Character)
}