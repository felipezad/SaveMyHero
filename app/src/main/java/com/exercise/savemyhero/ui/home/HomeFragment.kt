package com.exercise.savemyhero.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.exercise.savemyhero.databinding.FragmentHomeBinding
import com.exercise.savemyhero.domain.hero.Hero
import com.exercise.savemyhero.ui.core.BaseFragment
import com.exercise.savemyhero.ui.home.list.HomeListAdapter
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeListAdapter.OnItemClickListener {

    @Inject
    lateinit var homeViewModelFactory: HomeViewModel.Factory

    private val homeViewModel: HomeViewModel by viewModels {
        homeViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
    }

    private fun setupViewModel() {
        homeViewModel.text.observe(viewLifecycleOwner, Observer { t ->
            mViewBinding.textHome.text = t
        })

        homeViewModel.heroList.observe(viewLifecycleOwner, Observer { heroList ->
            heroList.forEachIndexed { index, hero ->
                Log.d("Hero", "$index -> $hero.id")
            }
        })
        homeViewModel.getListOfHeroes()
    }

    override fun getViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onItemClicked(hero: Hero, view: View) {
        TODO("Not yet implemented")
    }
}
