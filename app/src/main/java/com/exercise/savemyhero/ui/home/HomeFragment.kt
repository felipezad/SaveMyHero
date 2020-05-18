package com.exercise.savemyhero.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.savemyhero.databinding.FragmentHomeBinding
import com.exercise.savemyhero.domain.hero.Hero
import com.exercise.savemyhero.ui.core.BaseFragment
import com.exercise.savemyhero.ui.home.list.HomeHeroListAdapter
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeHeroListAdapter.OnItemClickListener {

    @Inject
    lateinit var homeViewModelFactory: HomeViewModel.Factory

    private val homeViewModel: HomeViewModel by viewModels {
        homeViewModelFactory
    }

    private val homeHeroListAdapter: HomeHeroListAdapter by lazy {
        HomeHeroListAdapter(onItemClickListener = this)
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
