package com.lbird.trendingapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lbird.trendingapp.data.Trending
import com.lbird.trendingapp.databinding.TrendingItemBinding
import kotlinx.android.synthetic.main.trending_item.view.*

class TrendingAdapter : androidx.recyclerview.widget.ListAdapter<Trending, TrendingAdapter.TrendingViewHolder>(TrendingComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val binding =
            TrendingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }

        val isVisible : Boolean = currentItem.visibility
        holder.itemView.expanded_layout.visibility = if (isVisible) View.VISIBLE else View.GONE

        holder.itemView.setOnClickListener {
            currentItem.visibility = !currentItem.visibility
            notifyItemChanged(position)

        }

    }

    class TrendingViewHolder(private val binding: TrendingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val imageUrl = "https://avatars.githubusercontent.com/u/16907830?s=40&v=4"
        val imagePath = binding.imageViewAvatar

        fun bind(trending: Trending) {
            binding.apply {
                Glide.with(itemView)
                    .load(imageUrl)
                    .into(imagePath)
                //textViewDescription.text = trending.description.plus(trending.url) ->have some NULL value
                //textViewLanguage.text = trending.language ->have some NULL value
                textViewUsername.text = trending.username
                textViewRepositoryName.text = trending.repositoryName
                textViewDescription.text = trending.url
                textViewStar.text = trending.totalStars
                textViewFork.text = trending.forks
            }
        }
    }

    class TrendingComparator : DiffUtil.ItemCallback<Trending>() {
        override fun areItemsTheSame(oldItem: Trending, newItem: Trending) =
            oldItem.username == newItem.username

        override fun areContentsTheSame(oldItem: Trending, newItem: Trending) =
            oldItem == newItem

    }

}