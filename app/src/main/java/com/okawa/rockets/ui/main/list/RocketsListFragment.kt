package com.okawa.rockets.ui.main.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.support.v7.widget.LinearLayoutManager
import com.okawa.rockets.R
import com.okawa.rockets.data.Result
import com.okawa.rockets.data.Status
import com.okawa.rockets.db.entity.RocketEntity
import com.okawa.rockets.ui.base.BaseFragment
import com.okawa.rockets.utils.ToastManager
import com.okawa.rockets.utils.adapter.RocketAdapter
import kotlinx.android.synthetic.main.fragment_rockets_list.*
import javax.inject.Inject

class RocketsListFragment: BaseFragment<RocketsListViewModel>() {

    @Inject
    lateinit var toastManager: ToastManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val rocketAdapter: RocketAdapter by lazy {
        RocketAdapter()
    }

    override fun defineViewModel() =
        ViewModelProviders.of(this, viewModelFactory).get(RocketsListViewModel::class.java)

    override fun layoutToInflate() = R.layout.fragment_rockets_list

    override fun doOnCreated() {
        initRocketListView()
        initSwipeRefresh()
        retrieveData()
    }

    private fun initRocketListView() {
        rclRocketsListFragmentContent.layoutManager = LinearLayoutManager(context)
        rclRocketsListFragmentContent.adapter = rocketAdapter
        rocketAdapter.setOnItemClickListener {
            getNavController().navigate(R.id.rocketDetailsFragment)
        }
    }

    private fun initSwipeRefresh() {
        swpRocketsListFragmentSwipeRefresh.isRefreshing = true
        swpRocketsListFragmentSwipeRefresh.setOnRefreshListener {
            retrieveData()
        }
        swpRocketsListFragmentSwipeRefresh.setColorSchemeResources(R.color.colorAccent)
    }

    private fun retrieveData() {
        viewModel.retrieveRockets().observe(this, Observer { result ->
            swpRocketsListFragmentSwipeRefresh.isRefreshing = false
            handleState(result)
        })
    }

    private fun handleState(result: Result<PagedList<RocketEntity>>?) {
        if(result == null) {
            toastManager.showToast(R.string.error_rockets_list_generic)
            return
        }

        when(result.status) {
            Status.SUCCESS -> {
                rocketAdapter.setData(result.data)
            }
            Status.ERROR -> {
                toastManager.showToast(result.message)
            }
            Status.LOADING -> { }
        }
    }
}