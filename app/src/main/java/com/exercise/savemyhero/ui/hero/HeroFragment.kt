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
import com.exercise.savemyhero.ui.core.BaseFragment
import com.exercise.savemyhero.ui.core.BundleKey
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
        heroViewModel.hero.observe(viewLifecycleOwner, Observer {
            mViewBinding.heroName.text = it.name
            mViewBinding.heroDescription.text = it.description
            requestManagerGlide
                .load(it?.thumbnail)
                .placeholder(R.drawable.ic_superhero_placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(mViewBinding.heroImage)
                .clearOnDetach()
        })
    }

    override fun setupView() {
        hero?.let {
            heroViewModel.displayHero(it)
        } ?: run {
            if (heroViewModel.hero.value == null) {
                val noHeroSelected = Hero(-1, "No hero was selected", "", "")
                heroViewModel.displayHero(noHeroSelected)
            }
        }

    }

    private fun setupViewWithoutHero() {

    }
}
