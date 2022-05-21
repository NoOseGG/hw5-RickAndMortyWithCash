package com.example.rickandmortywithcash.retrofit

import com.example.rickandmortywithcash.model.ListCharacters
import com.example.rickandmortywithcash.model.CharacterDetails
import com.example.rickandmortywithcash.model.Episode
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

    @GET("episode/{number}")
    suspend fun getEpisode(
        @Path("number") number: Int
    ): Episode
}