package com.example.rickandmortywithcash.service

import com.example.rickandmortywithcash.model.Character
import com.example.rickandmortywithcash.room.CharacterDao

class CharacterRepository(
    private val characterDao: CharacterDao
) : Repository {

    override suspend fun loadAllCharactersFromDb(): List<Character> {
        return characterDao.getAllCharactersFromDb().map { it.toCharacter() }
    }

    override suspend fun insertCharacterToDb(character: Character) {
        characterDao.insertCharacter(character.toCharacterDbEntity())
    }
}