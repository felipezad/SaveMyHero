package com.exercise.savemyhero.ui.home.list

import android.view.LayoutInflater
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
import com.exercise.savemyhero.domain.hero.imageUrl
import com.exercise.savemyhero.ui.core.BundleKey
import com.exercise.savemyhero.ui.core.ThumbnailOrientation.PORTRAIT
import com.exercise.savemyhero.ui.core.ThumbnailSize.MEDIUM

class HomeHeroListAdapter(
    private val onFavoriteButtonClick: OnFavoriteButtonClick,
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
        holder.bind(getItem(position), onFavoriteButtonClick)
    }

    interface OnFavoriteButtonClick {
        fun onFavoriteClicked(hero: Hero, shouldSave: Boolean)
    }

    class HomeHeroViewHolder(
        private val binding: HomeHeroItemListBinding,
        private val requestManager: RequestManager
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            hero: Hero,
            onFavoriteButtonClick: OnFavoriteButtonClick? = null
        ) {
            val missingDataString = binding.root.context.getString(R.string.hero_missing_data)
            binding.homeHeroName.text = hero.name
            binding.homeHeroDescription.text =
                if (hero.description.isNotEmpty()) hero.description else missingDataString
            requestManager
                .load(hero.imageUrl(PORTRAIT, MEDIUM))
                .placeholder(R.drawable.ic_superhero_placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.homeHeroThumbnail)
                .clearOnDetach()

            onFavoriteButtonClick?.let { listener ->
                binding.homeHeroFavoriteCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked)
                        listener.onFavoriteClicked(hero, true)
                    else
                        listener.onFavoriteClicked(hero, false)

                }
            }

            binding.root.setOnClickListener {
                val bundle = bundleOf(BundleKey.HERO_DETAIL.key to hero)
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_navigation_home_to_navigation_hero, bundle)
            }
        }
    }
}