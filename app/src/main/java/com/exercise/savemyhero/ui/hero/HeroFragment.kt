package com.exercise.savemyhero.ui.hero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.exercise.savemyhero.R
import com.exercise.savemyhero.domain.hero.Hero
import com.exercise.savemyhero.ui.core.BundleKey

class HeroFragment : Fragment() {

    private lateinit var heroViewModel: HeroViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        heroViewModel =
            ViewModelProviders.of(this).get(HeroViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_hero, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        heroViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val hero = arguments?.getParcelable<Hero>(BundleKey.HERO_DETAIL.key)
        return root
    }
}
