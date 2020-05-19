package com.exercise.savemyhero.ui.core

import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.exercise.savemyhero.R
import com.exercise.savemyhero.databinding.HeroItemListBinding
import com.exercise.savemyhero.domain.hero.Hero
import com.exercise.savemyhero.domain.hero.imageUrl

class HeroViewHolder(
    private val binding: HeroItemListBinding,
    private val requestManager: RequestManager
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        hero: Hero,
        onFavoriteButtonClick: OnFavoriteButtonClick? = null,
        isHeroFavorite: Boolean = true
    ) {
        val missingDataString = binding.root.context.getString(R.string.hero_missing_data)
        binding.homeHeroName.text = hero.name
        binding.homeHeroDescription.text =
            if (hero.description.isNotEmpty()) hero.description else missingDataString
        binding.homeHeroFavoriteCheckBox.isChecked = isHeroFavorite
        requestManager
            .load(hero.imageUrl(ThumbnailOrientation.PORTRAIT, ThumbnailSize.MEDIUM))
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

interface OnFavoriteButtonClick {
    fun onFavoriteClicked(hero: Hero, shouldSave: Boolean)
}