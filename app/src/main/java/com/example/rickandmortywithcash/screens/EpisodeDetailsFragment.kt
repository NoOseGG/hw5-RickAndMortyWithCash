package com.example.rickandmortywithcash.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.rickandmortywithcash.databinding.FragmentEpisodeDetailsBinding
import com.example.rickandmortywithcash.screens.viewmodel.ViewModelEpisodeDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodeDetailsFragment : Fragment() {
    
    private var _binding: FragmentEpisodeDetailsBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel = viewModel<ViewModelEpisodeDetails>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentEpisodeDetailsBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val episodeNumber = arguments?.getString(KEY_EPISODE_NUMBER)?.toInt()
        println(episodeNumber)
        if(episodeNumber != null) {
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                viewModel.value.getEpisode(episodeNumber)
            }
        }

        viewModel.value.episodeFlow.onEach {
            with(binding) {
                tvEpisodeNumber.text = it.episode
                tvAirDate.text = it.airDate
                tvCreated.text = it.created.substringBefore("T")
                println(it)
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY_EPISODE_NUMBER = "KEY_EPISODE_NUMBER"
    }
}