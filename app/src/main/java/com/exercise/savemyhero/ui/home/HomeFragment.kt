package com.exercise.savemyhero.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exercise.savemyhero.R
import com.exercise.savemyhero.databinding.FragmentHomeBinding
import com.exercise.savemyhero.domain.hero.Hero
import com.exercise.savemyhero.ui.core.BaseFragment
import com.exercise.savemyhero.ui.core.InfiniteRecyclerViewScrollListener
import com.exercise.savemyhero.ui.core.OnFavoriteButtonClick
import com.exercise.savemyhero.ui.home.list.HomeHeroListAdapter
import com.google.android.material.snackbar.Snackbar

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(), OnFavoriteButtonClick {

    private val homeHeroListAdapter: HomeHeroListAdapter by lazy {
        HomeHeroListAdapter(onFavoriteButtonClick = this, requestManager = requestManagerGlide)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mViewBinding.root
    }

    override fun setupViewModel() {
        mViewModel.heroList.observe(viewLifecycleOwner, Observer { heroList ->
            heroList.forEachIndexed { index, hero ->
                Log.d("Hero", "$index -> $hero.id")
            }
            val make = Snackbar.make(
                mViewBinding.root,
                getString(R.string.detail_save_favorite_hero_button),
                Snackbar.LENGTH_SHORT
            )
            make.animationMode = Snackbar.ANIMATION_MODE_FADE
            make.show()
            if (heroList.isEmpty()) {
                mViewBinding.layoutInternetProblemContainer.isVisible = true
                mViewBinding.homeHeroRecyclerView.isVisible = false
            } else {
                mViewBinding.homeHeroRecyclerView.isVisible = true
                mViewBinding.layoutInternetProblemContainer.isVisible = false
                homeHeroListAdapter.submitList(heroList)
            }

        })
        if (mViewModel.heroList.value.isNullOrEmpty())
            mViewModel.getListOfHeroes()
    }

    override fun setupView() {
        mViewModel =
            navGraphViewModels<HomeViewModel>(R.navigation.mobile_navigation) { mViewModelFactory }.value
        mViewBinding.homeHeroRecyclerView.apply {
            layoutManager = LinearLayoutManager(fragmentContext)
            adapter = homeHeroListAdapter
            addOnScrollListener(object :
                InfiniteRecyclerViewScrollListener(this.layoutManager as LinearLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                    mViewModel.getListOfHeroes(Companion.OFF_SET_TO_LOAD_MORE_HEROES_PER_PAGE * page)
                }
            })
        }

        mViewBinding.fabRetryLoadingHeroes.setOnClickListener {
            mViewModel.getListOfHeroes()
        }
    }

    override fun getViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onFavoriteClicked(hero: Hero, shouldSave: Boolean) {
        if (shouldSave)
            mViewModel.saveFavoriteHero(hero)
        else
            mViewModel.deleteFavoriteHero(hero)
    }

    companion object {
        private const val OFF_SET_TO_LOAD_MORE_HEROES_PER_PAGE = 5
    }
}
