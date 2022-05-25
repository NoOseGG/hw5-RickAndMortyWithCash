package com.example.rickandmortywithcash.service

import com.example.rickandmortywithcash.model.Character
import com.example.rickandmortywithcash.room.CharacterDao

class CharacterRepositoryImpl(
    private val characterDao: CharacterDao
) : CharacterRepository {

    override suspend fun loadAllCharactersFromDb(): List<Character> {
        return characterDao.getAllCharacters().map { it.toCharacter() }
    }

    override suspend fun insertCharacterToDb(character: Character) {
        characterDao.insertCharacter(character.toCharacterDbEntity())
    }
}