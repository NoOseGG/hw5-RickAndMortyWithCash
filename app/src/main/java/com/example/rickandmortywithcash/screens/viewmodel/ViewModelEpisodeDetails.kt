package com.example.rickandmortywithcash.screens.viewmodel

import androidx.lifecycle.ViewModel
import com.example.rickandmortywithcash.model.Episode
import com.example.rickandmortywithcash.service.Service
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ViewModelEpisodeDetails(
    private val service: Service
) : ViewModel() {

    var episodeFlow = MutableSharedFlow<Episode>()

    suspend fun getEpisode(number: Int) {
        val episode = service.loadEpisode(number)
        println(episode)
        episodeFlow.emit(episode)
    }
}