package com.example.rickandmortywithcash.service

import com.example.rickandmortywithcash.model.Character
import com.example.rickandmortywithcash.model.CharacterDetails
import com.example.rickandmortywithcash.model.ListCharacters

interface Service {

    suspend fun loadAllCharacters(page: Int): ListCharacters

    suspend fun loadCharacter(id: Int): CharacterDetails

    suspend fun loadAllCharactersFromDb(): List<Character>

    suspend fun insertCharacterToDb(character: Character)
}