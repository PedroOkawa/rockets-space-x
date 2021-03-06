package com.okawa.rockets.ui.main.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.okawa.rockets.R
import com.okawa.rockets.data.Result
import com.okawa.rockets.data.Status
import com.okawa.rockets.db.entity.RocketEntity
import com.okawa.rockets.ui.base.BaseFragment
import com.okawa.rockets.ui.main.details.RocketDetailsFragment
import com.okawa.rockets.utils.manager.ToastManager
import com.okawa.rockets.utils.adapter.RocketAdapter
import com.okawa.rockets.utils.bus.ConnectionBus
import kotlinx.android.synthetic.main.fragment_rockets_list.*
import javax.inject.Inject

class RocketsListFragment: BaseFragment<RocketsListViewModel>() {

    @Inject
    lateinit var connectionBus: ConnectionBus

    @Inject
    lateinit var toastManager: ToastManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val rocketAdapter: RocketAdapter by lazy {
        RocketAdapter()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_rockets_list, menu)
        menu?.let {
            if(viewModel.retrieveFilterValue() == true) {
                it.findItem(R.id.menu_filter_active).isChecked = true
            } else {
                it.findItem(R.id.menu_filter_all).isChecked = true
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        filterByActive(item?.itemId == R.id.menu_filter_active)
        item?.isChecked = true

        return super.onOptionsItemSelected(item)
    }

    override fun defineViewModel() =
        ViewModelProviders.of(this, viewModelFactory).get(RocketsListViewModel::class.java)

    override fun layoutToInflate() = R.layout.fragment_rockets_list

    override fun doOnCreated() {
        initRocketListView()
        initSwipeRefresh()
        retrieveData()
        initConnectionBus()
    }

    private fun initConnectionBus() {
        connectionBus.observe(this, Observer { _ ->
            filterByActive(viewModel.retrieveFilterValue())
        })
    }

    private fun initRocketListView() {
        rclRocketsListFragmentContent.layoutManager = LinearLayoutManager(context)
        rclRocketsListFragmentContent.adapter = rocketAdapter
        rocketAdapter.setOnItemClickListener {
            openDetails(it.rocketId)
        }
    }

    private fun initSwipeRefresh() {
        swpRocketsListFragmentSwipeRefresh.setColorSchemeResources(R.color.colorAccent)
        swpRocketsListFragmentSwipeRefresh.setOnRefreshListener {
            retrieveData()
        }
    }

    private fun filterByActive(filter: Boolean?) {
        viewModel.filterByActive(filter)
    }

    private fun retrieveData() {
        setLoading(true)
        viewModel.getRocketsLiveData().observe(this, Observer { result ->
            setLoading(false)
            handleStatus(result)
        })
    }

    private fun handleStatus(result: Result<PagedList<RocketEntity>>?) {
        if(result == null) {
            toastManager.showToast(R.string.error_rockets_list_generic)
            return
        }

        when(result.status) {
            Status.SUCCESS -> onSuccess(result.data)
            Status.ERROR -> onError(result.message)
            Status.LOADING -> setLoading(true)
        }
    }

    private fun onSuccess(data: PagedList<RocketEntity>?) {
        viwRocketsListFragmentError.visibility = View.GONE
        rocketAdapter.setData(data)
    }

    private fun onError(message: String?) {
        toastManager.showToast(message)
        viwRocketsListFragmentError.visibility = View.VISIBLE
    }

    private fun setLoading(isLoading: Boolean) {
        viwRocketsListFragmentError.visibility = View.GONE
        swpRocketsListFragmentSwipeRefresh.isRefreshing = isLoading
    }

    private fun openDetails(rocketId: String) {
        val bundle = Bundle()
        bundle.putString(RocketDetailsFragment.BUNDLE_DETAILS_ROCKET_ID, rocketId)
        getNavController().navigate(R.id.rocketDetailsFragment, bundle)
    }
}