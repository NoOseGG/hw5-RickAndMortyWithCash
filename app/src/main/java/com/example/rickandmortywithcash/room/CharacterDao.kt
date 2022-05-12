package com.example.rickandmortywithcash.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortywithcash.room.entity.CharacterDbEntity

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters")
    suspend fun getAllCharactersFromDb(): List<CharacterDbEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacter(characterDbEntity: CharacterDbEntity)
}