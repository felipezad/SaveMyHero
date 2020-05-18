package com.exercise.savemyhero.ui.home.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.exercise.savemyhero.R
import com.exercise.savemyhero.databinding.HomeHeroItemListBinding
import com.exercise.savemyhero.domain.hero.Hero
import com.exercise.savemyhero.ui.core.BundleKey

class HomeHeroListAdapter(
    private val onItemClickListener: OnItemClickListener,
    private val requestManager: RequestManager
) :
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
            ),
            requestManager = requestManager
        )
    }

    override fun onBindViewHolder(holder: HomeHeroViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClickListener)
    }

    interface OnItemClickListener {
        fun onItemClicked(hero: Hero, view: View)
    }

    class HomeHeroViewHolder(
        private val binding: HomeHeroItemListBinding,
        private val requestManager: RequestManager
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: Hero, onItemClickListener: HomeHeroListAdapter.OnItemClickListener? = null) {
            val missingDataString = binding.root.context.getString(R.string.hero_missing_data)
            binding.homeHeroName.text = hero.name
            binding.homeHeroDescription.text =
                if (hero.description.isNotEmpty()) hero.description else missingDataString
            requestManager
                .load(hero.thumbnail)
                .placeholder(R.drawable.ic_superhero_placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.homeHeroThumbnail)
                .clearOnDetach()

            binding.root.setOnClickListener {
                val bundle = bundleOf(BundleKey.HERO_DETAIL.key to hero)
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_navigation_home_to_navigation_hero, bundle)
            }
        }
    }
}