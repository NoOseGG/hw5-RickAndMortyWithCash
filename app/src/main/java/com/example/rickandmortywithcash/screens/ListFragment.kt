package com.example.rickandmoryapiwithroom.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmoryapiwithroom.adapter.CharacterAdapter
import com.example.rickandmoryapiwithroom.addPaginationScrollListener
import com.example.rickandmoryapiwithroom.databinding.FragmentListBinding
import com.example.rickandmoryapiwithroom.model.Character
import com.example.rickandmoryapiwithroom.screens.viewmodel.ViewModelFactory
import com.example.rickandmoryapiwithroom.screens.viewmodel.ViewModelList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var _vmList: ViewModelList? = null
    private val vmList get() = requireNotNull(_vmList)
    private var isLoading = false
    private var page = 1
    private val adapter by lazy {
        CharacterAdapter(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vmList = ViewModelProvider(this, ViewModelFactory()).get(ViewModelList::class.java)
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

        viewLifecycleOwner.lifecycleScope.launch {
            observeInLoading()
        }

        val linearlayout = LinearLayoutManager(requireContext())
        with(binding) {
            characterList.layoutManager = linearlayout
            characterList.adapter = adapter
            characterList.addPaginationScrollListener(linearlayout, ITEM_LOAD_TO_QUANTITY) {
                if(!isLoading) {
                    viewLifecycleOwner.lifecycleScope.launch {
                        vmList.loadAllCharacters(page)
                    }
                    page++
                }
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
                    println("EACH")
                }
                .collect()
        }
    }

    private fun observeInLoading() {
        lifecycleScope.launchWhenStarted {
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
        println(list)
    }

    companion object {
        const val ITEM_LOAD_TO_QUANTITY = 20
    }

}