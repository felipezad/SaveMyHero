package com.exercise.savemyhero.ui.core

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import dagger.android.support.DaggerFragment


abstract class BaseFragment<VB : ViewBinding> : DaggerFragment() {

    protected lateinit var mViewBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = getViewBinding()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    abstract fun getViewBinding(): VB
}