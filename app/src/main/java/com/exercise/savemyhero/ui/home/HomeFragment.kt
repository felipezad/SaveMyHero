package com.exercise.savemyhero.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.exercise.savemyhero.R
import com.exercise.savemyhero.ui.core.SaveMyHeroViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    @Inject
    lateinit var homeViewModelFactory: SaveMyHeroViewModelFactory

    private val homeViewModel: HomeViewModel by viewModels {
        homeViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}
