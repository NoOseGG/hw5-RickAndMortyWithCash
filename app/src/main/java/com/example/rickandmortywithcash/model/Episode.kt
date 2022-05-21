package com.example.rickandmortywithcash.model

import com.google.gson.annotations.SerializedName

data class Episode(
    val id: Int,
    @SerializedName("air_date")
    val airDate: String,
    val episode: String,
    val created: String
)