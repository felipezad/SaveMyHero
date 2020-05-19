package com.exercise.savemyhero.ui.hero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.exercise.savemyhero.databinding.FragmentHeroBinding
import com.exercise.savemyhero.domain.hero.Hero
import com.exercise.savemyhero.ui.core.BaseFragment
import com.exercise.savemyhero.ui.core.BundleKey
import javax.inject.Inject

class HeroFragment : BaseFragment<FragmentHeroBinding>() {

    @Inject
    lateinit var heroViewModelFactory: HeroViewModel.Factory

    private val heroViewModel: HeroViewModel by viewModels {
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
        heroViewModel.text.observe(viewLifecycleOwner, Observer {
            mViewBinding.heroName.text = it
        })
    }

    override fun setupView() {
        hero ?: setupViewWithoutHero()

    }

    private fun setupViewWithoutHero() {

    }
}
