package com.exercise.savemyhero.ui.core

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import dagger.android.support.DaggerFragment


abstract class BaseFragment<VB : ViewBinding> : DaggerFragment() {

    protected lateinit var mViewBinding: VB

    protected var fragmentContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = getViewBinding()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentContext = this.context
        setupView()
        setupViewModel()
    }

    abstract fun getViewBinding(): VB

    abstract fun setupViewModel()

    abstract fun setupView()
}