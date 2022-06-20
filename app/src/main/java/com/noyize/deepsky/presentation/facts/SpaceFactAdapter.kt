package com.noyize.deepsky.presentation.facts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.noyize.deepsky.databinding.ItemSpaceFactBinding
import com.noyize.deepsky.domain.model.SpaceFact

class SpaceFactAdapter(private val listener: ClickListener) :
    ListAdapter<SpaceFact, RecyclerView.ViewHolder>(DiffCallback) {

    interface ClickListener {
        fun onClick(position: Int,view : View)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemSpaceFactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpaceFactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SpaceFactViewHolder).bind(getItem(position), position)
    }

    inner class SpaceFactViewHolder(private val binding: ItemSpaceFactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(spaceFact: SpaceFact, position: Int) {
            with(binding) {
                thumbnail.load(spaceFact.imageUrl)
                title.text = spaceFact.title
                date.text = spaceFact.date
                ViewCompat.setTransitionName(thumbnail,spaceFact.imageUrl)
                root.setOnClickListener { listener.onClick(position,thumbnail) }
            }
        }
    }
}

object DiffCallback : DiffUtil.ItemCallback<SpaceFact>() {
    override fun areItemsTheSame(oldItem: SpaceFact, newItem: SpaceFact): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }

    override fun areContentsTheSame(oldItem: SpaceFact, newItem: SpaceFact): Boolean {
        return oldItem == newItem
    }
}