package com.exercise.savemyhero.ui.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.exercise.savemyhero.R
import com.exercise.savemyhero.databinding.FragmentFavoriteBinding
import com.exercise.savemyhero.domain.hero.Hero
import com.exercise.savemyhero.ui.core.BaseFragment
import com.exercise.savemyhero.ui.core.OnFavoriteButtonClick
import com.exercise.savemyhero.ui.favorite.list.FavoriteHeroListAdapter
import javax.inject.Inject

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(), OnFavoriteButtonClick {

    @Inject
    lateinit var favoriteViewModelFactory: FavoriteViewModel.Factory

    @Inject
    lateinit var requestManagerGlide: RequestManager

    private val favoriteViewModel: FavoriteViewModel by navGraphViewModels(R.id.mobile_navigation) {
        favoriteViewModelFactory
    }

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
        favoriteViewModel.heroList.observe(viewLifecycleOwner, Observer { heroList ->
            heroList.forEachIndexed { index, hero ->
                Log.d("Hero", "$index -> $hero.id")
            }
            favoriteHeroListAdapter.submitList(heroList)
        })
        if (favoriteViewModel.heroList.value == null)
            favoriteViewModel.getListOfHeroes()
    }

    override fun setupView() {
        mViewBinding.favoriteHeroRecyclerView.apply {
            layoutManager = LinearLayoutManager(fragmentContext)
            adapter = favoriteHeroListAdapter
        }
    }

    override fun onFavoriteClicked(hero: Hero, shouldSave: Boolean) {
        if (shouldSave)
            favoriteViewModel.saveFavoriteHero(hero)
        else
            favoriteViewModel.deleteFavoriteHero(hero)
    }
}
