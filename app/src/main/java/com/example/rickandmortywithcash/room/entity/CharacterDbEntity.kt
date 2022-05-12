package com.example.rickandmortywithcash.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmortywithcash.model.Character

@Entity(tableName = "characters")
data class CharacterDbEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "image")
    val image: String,
) {
    fun toCharacter(): Character = Character(
        id = id,
        name = name,
        image = image,
    )
}

