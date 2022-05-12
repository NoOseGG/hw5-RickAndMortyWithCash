package com.example.rickandmortywithcash.retrofit

import com.example.rickandmortywithcash.model.ListCharacters
import com.example.rickandmortywithcash.model.Character
import com.example.rickandmortywithcash.model.CharacterDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character/?")
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): ListCharacters

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int
    ): CharacterDetails
}