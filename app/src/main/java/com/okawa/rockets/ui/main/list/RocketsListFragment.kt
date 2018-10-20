package com.okawa.rockets.ui.main.list

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.widget.Toast
import com.okawa.rockets.R
import com.okawa.rockets.ui.base.BaseFragment
import javax.inject.Inject

class RocketsListFragment: BaseFragment<RocketsListViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun defineViewModel() =
        ViewModelProviders.of(this, viewModelFactory).get(RocketsListViewModel::class.java)

    override fun layoutToInflate() = R.layout.fragment_rockets_list

    override fun doOnCreated() {
        Toast.makeText(context, "MAIN ACTIVITY", Toast.LENGTH_SHORT).show()
        viewModel.test()
    }
}