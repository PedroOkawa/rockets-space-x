package com.okawa.rockets.ui.main.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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
        filterByActive(viewModel.retrieveFilterValue())
    }

    private fun initRocketListView() {
        rclRocketsListFragmentContent.layoutManager = LinearLayoutManager(context)
        rclRocketsListFragmentContent.adapter = rocketAdapter
        rocketAdapter.setOnItemClickListener {
            getNavController().navigate(R.id.rocketDetailsFragment)
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
            handleState(result)
        })
    }

    private fun handleState(result: Result<PagedList<RocketEntity>>?) {
        if(result == null) {
            toastManager.showToast(R.string.error_rockets_list_generic)
            return
        }

        when(result.status) {
            Status.SUCCESS -> onStatusSuccess(result.data)
            Status.ERROR -> onStatusError(result.message)
            Status.LOADING -> setLoading(true)
        }
    }

    private fun onStatusSuccess(data: PagedList<RocketEntity>?) {
        rocketAdapter.setData(data)
    }

    private fun onStatusError(message: String?) {
        toastManager.showToast(message)
    }

    private fun setLoading(isLoading: Boolean) {
        swpRocketsListFragmentSwipeRefresh.isRefreshing = isLoading
    }
}