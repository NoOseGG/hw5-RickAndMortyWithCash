package com.example.rickandmortywithcash.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortywithcash.R
import com.example.rickandmortywithcash.adapter.CharacterAdapter
import com.example.rickandmortywithcash.addPaginationScrollListener
import com.example.rickandmortywithcash.addSpaceDecoration
import com.example.rickandmortywithcash.databinding.FragmentListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import com.example.rickandmortywithcash.model.Character
import com.example.rickandmortywithcash.screens.viewmodel.ViewModelFactory
import com.example.rickandmortywithcash.screens.viewmodel.ViewModelList

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var _vmList: ViewModelList? = null
    private val vmList get() = requireNotNull(_vmList)
    private var isLoading = false
    private var page = 1
    private val adapter by lazy {
        CharacterAdapter(requireContext()) {
            findNavController().navigate(
                R.id.action_listFragment_to_detailsFragment,
                bundleOf(
                    DetailsFragment.KEY_CHARACTER_ID to it.id,
                    DetailsFragment.KEY_NAME_CHARACTER to it.name
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
            ViewModelFactory(requireContext())
        ).get(ViewModelList::class.java)
        return FragmentListBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            vmList.loadAllCharacters(page)
            page++
            observeFlow()
        }

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            observeInLoading()
        }

        val linearlayout = LinearLayoutManager(requireContext())
        with(binding) {
            characterList.layoutManager = linearlayout
            characterList.adapter = adapter
            characterList.addSpaceDecoration(SPACE_DECORATION)
            characterList.addPaginationScrollListener(linearlayout, ITEM_LOAD_TO_QUANTITY) {
                if (!isLoading) {
                    viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                        vmList.loadAllCharacters(page)
                        page++
                    }
                    println(page)
                }
            }

            swipeRefresh.setOnRefreshListener {
                refreshList()
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeFlow() {
        lifecycleScope.launchWhenStarted {
            vmList.charactersList
                .onEach { list ->
                    updateUi(list)
                }
                .collect()
        }
    }

    private fun observeInLoading() {
        lifecycleScope.launchWhenStarted() {
            vmList.isLoading
                .onEach {
                    isLoading = it
                }
                .collect()
        }
    }

    private fun updateUi(list: List<Character>) {
        adapter.submitList(list)
        binding.characterList.adapter = adapter
    }

    private fun refreshList() {
        page = 1
        val listPersonEmpty = listOf<Character>()
        adapter.submitList(listPersonEmpty)
        viewLifecycleOwner.lifecycleScope.launch {
            vmList.loadAllCharacters(page)
            observeFlow()
        }
        page++
        showToast("Data updated")
        binding.swipeRefresh.isRefreshing = false
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
            .show()
    }

    companion object {
        const val ITEM_LOAD_TO_QUANTITY = 20
        const val SPACE_DECORATION = 20
    }
}