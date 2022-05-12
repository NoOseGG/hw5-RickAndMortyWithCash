package com.example.rickandmortywithcash.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmortywithcash.model.Character
import com.example.rickandmortywithcash.databinding.CharacterItemBinding

class CharacterAdapter(
    private val context: Context,
    private val onClick: (Character) -> Unit
) : ListAdapter<Character, CharacterViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            binding = CharacterItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            ),
            onClick = onClick,
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }

        }
    }
}

class CharacterViewHolder(
    private val binding: CharacterItemBinding,
    private val onClick: (Character) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Character) {
        binding.imgAvatar.load(item.image)
        binding.tvName.text = item.name
        binding.root.setOnClickListener {
            onClick(item)
        }
    }
}
