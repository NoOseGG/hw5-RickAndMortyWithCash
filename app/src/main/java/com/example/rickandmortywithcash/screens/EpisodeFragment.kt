package com.example.rickandmortywithcash.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmortywithcash.R
import com.example.rickandmortywithcash.adapter.EpisodeAdapter
import com.example.rickandmortywithcash.databinding.FragmentEpisodeBinding
import com.example.rickandmortywithcash.screens.viewmodel.ViewModelEpisodeDetails
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodeFragment : Fragment() {
    
    private var _binding: FragmentEpisodeBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel = viewModel<ViewModelEpisodeDetails>()
    private val adapter by lazy {
        EpisodeAdapter(requireContext()) {
            findNavController().navigate(
                R.id.action_episodeFragment_to_episodeDetailsFragment,
                bundleOf(
                    EpisodeDetailsFragment.KEY_EPISODE_NUMBER to it
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentEpisodeBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listEpisode = arguments?.getSerializable(KEY_CHARACTER_EPISODE) as List<String>
        val list = listEpisode.map {
            it.substringAfterLast("/")
        }
        with(binding) {
            recyclerViewEpisodes.layoutManager = GridLayoutManager(requireContext(), 3)
            recyclerViewEpisodes.adapter = adapter
            adapter.submitList(list)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY_CHARACTER_EPISODE = "KEY_CHARACTER_EPISODE"
        const val KEY_CHARACTER_NAME = "characterName"
    }
    
}