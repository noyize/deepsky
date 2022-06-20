package com.noyize.deepsky.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.noyize.deepsky.app.util.animateAndShow
import com.noyize.deepsky.databinding.ItemSpaceFactDetailBinding
import com.noyize.deepsky.domain.model.SpaceFact
import dev.chrisbanes.insetter.applyInsetter

class SpaceFactDetailAdapter(private val listener : ClickListener) :
    ListAdapter<SpaceFact, RecyclerView.ViewHolder>(SpaceFactDetailDiffCallback) {

    interface ClickListener{
        fun onMoreDetailClick(spaceFact: SpaceFact)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemSpaceFactDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpaceFactDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SpaceFactDetailViewHolder).bind(getItem(position))
    }

    inner class SpaceFactDetailViewHolder(private val binding: ItemSpaceFactDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(spaceFact: SpaceFact) {
            with(binding) {
                spaceFactDetailImage.load(spaceFact.imageUrl)
                title.text = spaceFact.title
                date.text = spaceFact.date
                ViewCompat.setTransitionName(spaceFactDetailImage,spaceFact.imageUrl)
                learnMore.setOnClickListener { listener.onMoreDetailClick(spaceFact) }
                detailView.animateAndShow()
            }
        }
    }
}

object SpaceFactDetailDiffCallback : DiffUtil.ItemCallback<SpaceFact>() {
    override fun areItemsTheSame(oldItem: SpaceFact, newItem: SpaceFact): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }

    override fun areContentsTheSame(oldItem: SpaceFact, newItem: SpaceFact): Boolean {
        return oldItem == newItem
    }
}