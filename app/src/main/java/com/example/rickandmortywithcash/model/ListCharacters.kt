package com.example.rickandmortywithcash.model

data class ListCharacters(
    val info: Info,
    val results: List<Character>,
)

data class Info(
    val pages: Int,
)