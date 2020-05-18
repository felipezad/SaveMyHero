package com.exercise.savemyhero.ui.home.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.exercise.savemyhero.databinding.HomeHeroItemListBinding
import com.exercise.savemyhero.domain.hero.Hero

class HomeHeroListAdapter(private val onItemClickListener: OnItemClickListener) :
    ListAdapter<Hero, HomeHeroListAdapter.HomeHeroViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Hero>() {
            override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHeroViewHolder {
        return HomeHeroViewHolder(
            binding = HomeHeroItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeHeroViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClickListener)
    }

    interface OnItemClickListener {
        fun onItemClicked(hero: Hero, view: View)
    }

    class HomeHeroViewHolder(private val binding: HomeHeroItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(hero: Hero, onItemClickListener: HomeHeroListAdapter.OnItemClickListener? = null) {
            binding.homeHeroName.text = hero.name
            //TODO (implement glide)
//            binding.imageView.load(post.imageUrl) {
//                placeholder(R.drawable.ic_photo)
//                error(R.drawable.ic_broken_image)
//            }

            onItemClickListener?.let { listener ->
                binding.root.setOnClickListener {
                    listener.onItemClicked(hero, binding.root)
                }
            }
        }
    }
}