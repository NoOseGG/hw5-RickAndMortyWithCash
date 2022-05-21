package com.example.rickandmortywithcash.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortywithcash.databinding.EpisodeItemBinding

class EpisodeAdapter(
    context: Context,
    private val onClick: (String) -> Unit
) : ListAdapter<String, EpisodeViewHolder>(DIFF_UTIL) {

    private val inflater = LayoutInflater.from(context)

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(
            binding = EpisodeItemBinding.inflate(inflater, parent, false),
            onClick = onClick
        )
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class EpisodeViewHolder(
    private val binding: EpisodeItemBinding,
    private val onClick: (String) -> Unit
    ): RecyclerView.ViewHolder(binding.root){

    fun bind(item: String) {
        binding.tvEpisode.text = item
        binding.root.setOnClickListener {
            onClick(item)
        }
    }
}
