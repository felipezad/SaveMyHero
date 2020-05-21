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
import com.bumptech.glide.RequestManager
import com.exercise.savemyhero.R
import com.exercise.savemyhero.databinding.FragmentHomeBinding
import com.exercise.savemyhero.domain.hero.Hero
import com.exercise.savemyhero.ui.core.BaseFragment
import com.exercise.savemyhero.ui.core.InfiniteRecyclerViewScrollListener
import com.exercise.savemyhero.ui.core.OnFavoriteButtonClick
import com.exercise.savemyhero.ui.home.list.HomeHeroListAdapter
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding>(), OnFavoriteButtonClick {

    @Inject
    lateinit var homeViewModelFactory: HomeViewModel.Factory

    @Inject
    lateinit var requestManagerGlide: RequestManager

    private val homeViewModel: HomeViewModel by navGraphViewModels(R.id.mobile_navigation) {
        homeViewModelFactory
    }

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
        homeViewModel.heroList.observe(viewLifecycleOwner, Observer { heroList ->
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
        if (homeViewModel.heroList.value.isNullOrEmpty())
            homeViewModel.getListOfHeroes()
    }

    override fun setupView() {
        mViewBinding.homeHeroRecyclerView.apply {
            layoutManager = LinearLayoutManager(fragmentContext)
            adapter = homeHeroListAdapter
            addOnScrollListener(object :
                InfiniteRecyclerViewScrollListener(this.layoutManager as LinearLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                    homeViewModel.getListOfHeroes(Companion.OFF_SET_TO_LOAD_MORE_HEROES_PER_PAGE * page)
                }
            })
        }

        mViewBinding.fabRetryLoadingHeroes.setOnClickListener {
            homeViewModel.getListOfHeroes()
        }
    }

    override fun getViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onFavoriteClicked(hero: Hero, shouldSave: Boolean) {
        if (shouldSave)
            homeViewModel.saveFavoriteHero(hero)
        else
            homeViewModel.deleteFavoriteHero(hero)
    }

    companion object {
        private const val OFF_SET_TO_LOAD_MORE_HEROES_PER_PAGE = 5
    }
}
