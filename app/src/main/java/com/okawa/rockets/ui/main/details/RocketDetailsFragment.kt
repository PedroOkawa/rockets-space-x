package com.okawa.rockets.ui.main.details

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.view.MenuItem
import com.okawa.rockets.R
import com.okawa.rockets.ui.base.BaseFragment
import javax.inject.Inject

class RocketDetailsFragment: BaseFragment<RocketDetailsViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun defineViewModel() =
        ViewModelProviders.of(this, viewModelFactory).get(RocketDetailsViewModel::class.java)

    override fun layoutToInflate() = R.layout.fragment_rocket_details

    override fun doOnCreated() {
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home) {
            getNavController().navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }
}