package com.exercise.savemyhero.ui.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.savemyhero.R
import com.exercise.savemyhero.databinding.FragmentFavoriteBinding
import com.exercise.savemyhero.domain.hero.Hero
import com.exercise.savemyhero.ui.core.BaseFragment
import com.exercise.savemyhero.ui.core.OnFavoriteButtonClick
import com.exercise.savemyhero.ui.favorite.list.FavoriteHeroListAdapter

class FavoriteFragment : BaseFragment<FavoriteViewModel, FragmentFavoriteBinding>(),
    OnFavoriteButtonClick {

    override val mViewModel: FavoriteViewModel by navGraphViewModels(R.id.mobile_navigation) { mViewModelFactory }

    private val favoriteHeroListAdapter: FavoriteHeroListAdapter by lazy {
        FavoriteHeroListAdapter(onFavoriteButtonClick = this, requestManager = requestManagerGlide)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mViewBinding.root
    }

    override fun getViewBinding(): FragmentFavoriteBinding {
        return FragmentFavoriteBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        mViewModel.heroList.observe(viewLifecycleOwner, Observer { heroList ->
            heroList.forEachIndexed { index, hero ->
                Log.d("Hero", "$index -> $hero.id")
            }
            if (heroList.isNotEmpty()) {
                favoriteHeroListAdapter.submitList(heroList)
                changeToHeroesLayout(mViewBinding.viewSwitcherHomeScreen.displayedChild)
            } else {
                mViewBinding.viewSwitcherHomeScreen.displayedChild = HERO_LAYOUT_DATABASE_EMPTY
            }
        })
        mViewModel.getListOfHeroes()
    }

    override fun setupView() {
        mViewBinding.favoriteHeroRecyclerView.apply {
            layoutManager = LinearLayoutManager(fragmentContext)
            adapter = favoriteHeroListAdapter
        }
    }

    override fun onFavoriteClicked(hero: Hero, shouldSave: Boolean) {
        if (shouldSave)
            mViewModel.saveFavoriteHero(hero)
        else
            mViewModel.deleteFavoriteHero(hero)
    }

    private fun changeToHeroesLayout(displayedChild: Int) {
        if (displayedChild == HERO_LAYOUT_DATABASE_EMPTY) {
            mViewBinding.viewSwitcherHomeScreen.displayedChild = HERO_LAYOUT_DATABASE_SUCCESS
        }
    }

    companion object {
        private const val HERO_LAYOUT_DATABASE_EMPTY = 0
        private const val HERO_LAYOUT_DATABASE_SUCCESS = 1
    }
}
