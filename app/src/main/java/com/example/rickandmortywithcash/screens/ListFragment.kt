package com.example.rickandmortywithcash.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortywithcash.R
import com.example.rickandmortywithcash.addSpaceDecoration
import com.example.rickandmortywithcash.databinding.FragmentListBinding
import kotlinx.coroutines.launch
import com.example.rickandmortywithcash.paging.CharacterDataAdapter
import com.example.rickandmortywithcash.screens.viewmodel.ViewModelFactory
import com.example.rickandmortywithcash.screens.viewmodel.ViewModelList
import kotlinx.coroutines.flow.collectLatest

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var _vmList: ViewModelList? = null
    private val vmList get() = requireNotNull(_vmList)
    private val adapter by lazy {
        CharacterDataAdapter(requireContext()) {
            findNavController().navigate(
                R.id.action_listFragment_to_detailsFragment,
                bundleOf(
                    DetailsFragment.KEY_CHARACTER_ID to it.id,
                    DetailsFragment.KEY_NAME_CHARACTER to it.name,
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vmList = ViewModelProvider(
            this,
            ViewModelFactory()
        ).get(ViewModelList::class.java)
        return FragmentListBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
            recyclerView.addSpaceDecoration(SPACE_DECORATION)
        }

        lifecycleScope.launch {
            vmList.characters.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val SPACE_DECORATION = 20
    }
}