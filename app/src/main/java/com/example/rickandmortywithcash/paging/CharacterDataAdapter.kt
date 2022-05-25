package com.example.rickandmortywithcash.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmortywithcash.databinding.CharacterItemBinding
import com.example.rickandmortywithcash.model.Character

class CharacterDataAdapter(
    context: Context,
    private val onClick: (Character) -> Unit,
) : PagingDataAdapter<Character, CharacterViewHolder>(DIFF_UTIL) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(item) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            binding = CharacterItemBinding.inflate(layoutInflater, parent, false),
            onClick = onClick
        )
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
