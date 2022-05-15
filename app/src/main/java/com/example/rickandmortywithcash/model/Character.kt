package com.example.rickandmortywithcash.model

import com.example.rickandmortywithcash.room.entity.CharacterDbEntity
import java.io.Serializable

data class Character(
    val id: Int,
    val name: String,
    val image: String,
) : Serializable {
    fun toCharacterDbEntity(): CharacterDbEntity = CharacterDbEntity(
        id = id,
        name = name,
        image = image,
    )
}
