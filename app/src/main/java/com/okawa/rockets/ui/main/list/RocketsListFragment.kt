package com.okawa.rockets.ui.main.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.widget.Toast
import com.okawa.rockets.R
import com.okawa.rockets.data.Result
import com.okawa.rockets.data.Status
import com.okawa.rockets.db.entity.RocketEntity
import com.okawa.rockets.ui.base.BaseFragment
import javax.inject.Inject

class RocketsListFragment: BaseFragment<RocketsListViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun defineViewModel() =
        ViewModelProviders.of(this, viewModelFactory).get(RocketsListViewModel::class.java)

    override fun layoutToInflate() = R.layout.fragment_rockets_list

    override fun doOnCreated() {
        retrieveData()
    }

    private fun retrieveData() {
        viewModel.retrieveRockets().observe(this, Observer { result ->
            handleState(result)
        })
    }
    private fun handleState(result: Result<PagedList<RocketEntity>>?) {
        if(result == null) {
            Toast.makeText(context, "Generic Error", Toast.LENGTH_SHORT).show()
            return
        }
        when(result.status) {
            Status.SUCCESS -> {
                Toast.makeText(context, "TEST: ${result.data}", Toast.LENGTH_SHORT).show()
            }
            Status.ERROR -> {
                Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
            }
            Status.LOADING -> { }
        }
    }
}