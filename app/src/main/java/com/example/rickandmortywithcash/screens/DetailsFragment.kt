package com.example.rickandmortywithcash.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.rickandmortywithcash.R
import com.example.rickandmortywithcash.databinding.FragmentDetailsBinding
import com.example.rickandmortywithcash.service.ServiceLocator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val service by lazy {
        ServiceLocator.getInstanceService()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDetailsBinding.inflate(inflater, container, false)
        .also { _binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getInt(KEY_CHARACTER_ID)

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val character = id?.let { service.loadCharacter(it) }
            launch(Dispatchers.Main) {
                with(binding) {
                    if (character != null) {
                        imgAvatarDetails.load(character.image)
                        name.text = character.name
                        status.text = getString(R.string.status, character.status)
                        species.text = getString(R.string.species, character.species)
                        type.text = getString(R.string.type, character.type)
                        gender.text = getString(R.string.gender, character.gender)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY_CHARACTER_ID = "KEY_CHARACTER_ID"
        const val KEY_NAME_CHARACTER = "characterName"
    }
}