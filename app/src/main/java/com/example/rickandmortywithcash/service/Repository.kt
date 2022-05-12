package com.example.rickandmortywithcash.service

import com.example.rickandmortywithcash.room.entity.CharacterDbEntity
import com.example.rickandmortywithcash.model.Character

interface Repository {

    suspend fun loadAllCharactersFromDb(): List<Character>

    suspend fun insertCharacterToDb(character: Character)
}