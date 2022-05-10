package com.example.rickandmoryapiwithroom.service

import com.example.rickandmoryapiwithroom.model.Character
import com.example.rickandmoryapiwithroom.model.ListCharacters

interface Service {

    suspend fun loadAllCharacters(page: Int): ListCharacters

    suspend fun loadCharacter(id: Int): Character

}