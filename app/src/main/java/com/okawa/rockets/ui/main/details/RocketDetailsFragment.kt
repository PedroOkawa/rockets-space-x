package com.okawa.rockets.ui.main.details

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.okawa.rockets.R
import com.okawa.rockets.data.Result
import com.okawa.rockets.data.Status
import com.okawa.rockets.db.entity.LaunchEntity
import com.okawa.rockets.db.entity.RocketEntity
import com.okawa.rockets.ui.base.BaseFragment
import com.okawa.rockets.utils.DateUtils
import com.okawa.rockets.utils.manager.ToastManager
import com.okawa.rockets.utils.adapter.LaunchAdapter
import com.okawa.rockets.utils.bus.ConnectionBus
import kotlinx.android.synthetic.main.fragment_rocket_details.*
import javax.inject.Inject

class RocketDetailsFragment: BaseFragment<RocketDetailsViewModel>() {

    companion object {
        const val BUNDLE_DETAILS_ROCKET_ID = "rocketId"
    }

    @Inject
    lateinit var connectionBus: ConnectionBus

    @Inject
    lateinit var dateUtils: DateUtils

    @Inject
    lateinit var toastManager: ToastManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val launchAdapter: LaunchAdapter by lazy {
        LaunchAdapter(dateUtils)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home) {
            getNavController().navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun defineViewModel() =
        ViewModelProviders.of(this, viewModelFactory).get(RocketDetailsViewModel::class.java)

    override fun layoutToInflate() = R.layout.fragment_rocket_details

    override fun doOnCreated() {
        retrieveRocketData()
        retrieveLaunchData()
        initConnectionBus()
    }

    private fun initConnectionBus() {
        connectionBus.observe(this, Observer { _ ->
            defineRocketId()
        })
    }

    private fun retrieveRocketData() {
        viewModel.getRocketLiveData().observe(this, Observer { result ->
            handleRocketStatus(result)
        })
    }

    private fun retrieveLaunchData() {
        viewModel.getLaunchLiveData().observe(this, Observer { result ->
            handleLaunchStatus(result)
        })
    }

    private fun defineRocketId() {
        val rocketId = arguments?.getString(BUNDLE_DETAILS_ROCKET_ID)
        viewModel.defineRocketId(rocketId)
    }

    private fun handleRocketStatus(result: Result<RocketEntity>?) {
        if(result == null) {
            toastManager.showToast(R.string.error_rockets_list_generic)
            return
        }

        when(result.status) {
            Status.SUCCESS -> onRocketSuccess(result.data)
            Status.ERROR -> onRocketError(result.message)
            Status.LOADING -> onRocketLoading()
        }
    }

    private fun handleLaunchStatus(result: Result<List<LaunchEntity>>?) {
        if(result == null) {
            toastManager.showToast(R.string.error_rockets_list_generic)
            return
        }

        when(result.status) {
            Status.SUCCESS -> {
                setLaunchLoading(false)
                onLaunchSuccess(result.data)
            }
            Status.ERROR -> {
                setLaunchLoading(false)
                onLaunchError(result.message)
            }
            Status.LOADING -> setLaunchLoading(true)
        }
    }

    private fun onRocketSuccess(data: RocketEntity?) {
        viwRocketDetailsFragmentError.visibility = View. GONE
        txtRocketDetailsFragmentName.text = data?.name
        txtRocketDetailsFragmentDescription.text = data?.description
    }

    private fun onRocketError(message: String?) {
        toastManager.showToast(message)
        viwRocketDetailsFragmentError.visibility = View. VISIBLE
    }

    private fun onRocketLoading() {
        viwRocketDetailsFragmentError.visibility = View. GONE
    }

    private fun onLaunchSuccess(data: List<LaunchEntity>?) {
        rclRocketDetailsFragmentLaunches.layoutManager = LinearLayoutManager(context)
        rclRocketDetailsFragmentLaunches.adapter = launchAdapter
        launchAdapter.setData(data)
    }

    private fun onLaunchError(message: String?) {
        toastManager.showToast(message)
    }

    private fun setLaunchLoading(isLoading: Boolean) {
        if(isLoading) {
            cntRocketDetailsFragmentLaunchesLoading.show()
        } else {
            cntRocketDetailsFragmentLaunchesLoading.hide()
        }
    }
}