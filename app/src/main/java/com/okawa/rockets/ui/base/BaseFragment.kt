package com.okawa.rockets.ui.base

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<VM: ViewModel>: Fragment() {

    protected val viewModel: VM by lazy {
        defineViewModel()
    }

    @LayoutRes
    abstract fun layoutToInflate(): Int

    abstract fun doOnCreated()

    abstract fun defineViewModel(): VM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(layoutToInflate(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doOnCreated()
    }

    protected fun getNavController() = NavHostFragment.findNavController(this)

}