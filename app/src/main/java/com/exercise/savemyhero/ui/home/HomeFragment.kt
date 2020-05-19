package com.exercise.savemyhero.ui.home

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
import com.exercise.savemyhero.databinding.FragmentHomeBinding
import com.exercise.savemyhero.domain.hero.Hero
import com.exercise.savemyhero.ui.core.BaseFragment
import com.exercise.savemyhero.ui.home.list.HomeHeroListAdapter
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeHeroListAdapter.OnItemClickListener {

    @Inject
    lateinit var homeViewModelFactory: HomeViewModel.Factory

    @Inject
    lateinit var requestManagerGlide: RequestManager

    private val homeViewModel: HomeViewModel by navGraphViewModels(R.id.mobile_navigation) {
        homeViewModelFactory
    }

    private val homeHeroListAdapter: HomeHeroListAdapter by lazy {
        HomeHeroListAdapter(onItemClickListener = this, requestManager = requestManagerGlide)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mViewBinding.root
    }

    override fun setupViewModel() {
        homeViewModel.text.observe(viewLifecycleOwner, Observer { t ->
            mViewBinding.textHome.text = t
        })

        homeViewModel.heroList.observe(viewLifecycleOwner, Observer { heroList ->
            heroList.forEachIndexed { index, hero ->
                Log.d("Hero", "$index -> $hero.id")
            }
            homeHeroListAdapter.submitList(heroList)
        })
        if (homeViewModel.heroList.value == null)
            homeViewModel.getListOfHeroes()
    }

    override fun setupView() {
        mViewBinding.homeHeroRecyclerView.apply {
            layoutManager = LinearLayoutManager(fragmentContext)
            adapter = homeHeroListAdapter
        }
    }

    override fun getViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onItemClicked(hero: Hero, view: View) {
        TODO("Not yet implemented")
    }
}
