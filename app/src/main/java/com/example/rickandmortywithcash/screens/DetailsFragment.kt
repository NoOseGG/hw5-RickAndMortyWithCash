package com.example.rickandmortywithcash.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.rickandmortywithcash.R
import com.example.rickandmortywithcash.databinding.FragmentDetailsBinding
import com.example.rickandmortywithcash.screens.viewmodel.ViewModelDetailsFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: ViewModelDetailsFragment by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDetailsBinding.inflate(inflater, container, false)
        .also { _binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = args.characterId
        var episodes: List<String> = emptyList()
        var characterName: String = ""

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadCharacter(id)
        }

        viewModel.characterFlow.onEach { character ->
            with(binding) {
                imgAvatarDetails.load(character.image)
                name.text = character.name
                status.text = getString(R.string.status, character.status)
                species.text = getString(R.string.species, character.species)
                type.text = getString(R.string.type, character.type)
                gender.text = getString(R.string.gender, character.gender)
            }
            episodes = character.episode
            characterName = character.name
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.buttonEpisodes.setOnClickListener {
            println(episodes)
            findNavController().navigate(
                R.id.action_detailsFragment_to_episodeFragment,
                bundleOf(
                    EpisodeFragment.KEY_CHARACTER_EPISODE to episodes,
                    EpisodeFragment.KEY_CHARACTER_NAME to characterName
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}