package com.exercise.savemyhero.ui.hero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.exercise.savemyhero.R
import com.exercise.savemyhero.databinding.FragmentHeroBinding
import com.exercise.savemyhero.domain.hero.Hero
import com.exercise.savemyhero.domain.hero.imageUrl
import com.exercise.savemyhero.ui.core.BaseFragment
import com.exercise.savemyhero.ui.core.BundleKey
import com.exercise.savemyhero.ui.core.ThumbnailOrientation.LANDSCAPE
import com.exercise.savemyhero.ui.core.ThumbnailSize.LARGE
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class HeroFragment : BaseFragment<FragmentHeroBinding>() {

    @Inject
    lateinit var heroViewModelFactory: HeroViewModel.Factory

    @Inject
    lateinit var requestManagerGlide: RequestManager

    private val heroViewModel: HeroViewModel by navGraphViewModels(R.id.mobile_navigation) {
        heroViewModelFactory
    }

    private var hero: Hero? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        hero = arguments?.getParcelable<Hero>(BundleKey.HERO_DETAIL.key)
        return mViewBinding.root
    }

    override fun getViewBinding(): FragmentHeroBinding {
        return FragmentHeroBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        heroViewModel.hero.observe(viewLifecycleOwner, Observer { observableHero ->
            mViewBinding.heroName.text = observableHero.name
            mViewBinding.heroDescription.text = observableHero.description

            mViewBinding.heroFavoriteButton.setOnClickListener {
                heroViewModel.saveFavoriteHero(observableHero)
            }
            requestManagerGlide
                .load(observableHero?.imageUrl(LANDSCAPE, LARGE))
                .placeholder(R.drawable.ic_superhero_placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(mViewBinding.heroImage)
                .clearOnDetach()
        })

        heroViewModel.favoriteButtonResult.observe(
            viewLifecycleOwner, Observer { favoriteButton ->
                if (favoriteButton) {
                    val make = Snackbar.make(
                        mViewBinding.root,
                        getString(R.string.detail_save_favorite_hero_button),
                        Snackbar.LENGTH_SHORT
                    )
                    make.animationMode = Snackbar.ANIMATION_MODE_FADE
                    make.show()
                }
            })
    }

    override fun setupView() {
        hero?.let {
            heroViewModel.displayHero(it)
            return
        }
        if (heroViewModel.hero.value == null) {
            val noHeroSelected = Hero(-1, "No hero selected", "", "")
            heroViewModel.displayHero(noHeroSelected)
        }
    }
}
