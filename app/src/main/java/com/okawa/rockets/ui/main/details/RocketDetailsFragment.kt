package com.okawa.rockets.ui.main.details

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.okawa.rockets.R
import com.okawa.rockets.data.Result
import com.okawa.rockets.data.Status
import com.okawa.rockets.db.entity.LaunchEntity
import com.okawa.rockets.db.entity.RocketEntity
import com.okawa.rockets.ui.base.BaseFragment
import com.okawa.rockets.utils.ToastManager
import com.okawa.rockets.utils.adapter.LaunchAdapter
import kotlinx.android.synthetic.main.fragment_rocket_details.*
import javax.inject.Inject

class RocketDetailsFragment: BaseFragment<RocketDetailsViewModel>() {

    companion object {
        const val BUNDLE_DETAILS_ROCKET_ID = "rocketId"
    }

    @Inject
    lateinit var toastManager: ToastManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val launchAdapter: LaunchAdapter by lazy {
        LaunchAdapter()
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
        viewModel.getRocketLiveData().observe(this, Observer { result ->
            handleRocketStatus(result)
        })
        viewModel.getLaunchLiveData().observe(this, Observer { result ->
            handleLaunchStatus(result)
        })
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
            Status.ERROR -> onError(result.message)
            Status.LOADING -> setLoading(true)
        }
    }

    private fun handleLaunchStatus(result: Result<List<LaunchEntity>>?) {
        if(result == null) {
            toastManager.showToast(R.string.error_rockets_list_generic)
            return
        }

        when(result.status) {
            Status.SUCCESS -> onLaunchSuccess(result.data)
            Status.ERROR -> onError(result.message)
            Status.LOADING -> setLoading(true)
        }
    }

    private fun onRocketSuccess(data: RocketEntity?) {
        txtRocketDetailsFragmentDescription.text = data?.description
    }

    private fun onLaunchSuccess(data: List<LaunchEntity>?) {
        val lineData = LineData()
        data?.sortedBy {
            it.launchDate
        }?.forEachIndexed { index, launch ->
            val entry = Entry(index.toFloat(), 2f)

            lineData.addEntry(entry, index)
        }
        lncRocketDetailsFragmentLaunches.setDrawGridBackground(false)
        lncRocketDetailsFragmentLaunches.data = lineData
        lncRocketDetailsFragmentLaunches.data.notifyDataChanged()
        lncRocketDetailsFragmentLaunches.notifyDataSetChanged()
        lncRocketDetailsFragmentLaunches.invalidate()

        rclRocketDetailsFragmentContent.layoutManager = LinearLayoutManager(context)
        rclRocketDetailsFragmentContent.adapter = launchAdapter
        launchAdapter.setData(data)
    }

    private fun onError(message: String?) {
        toastManager.showToast(message)
    }

    private fun setLoading(isLoading: Boolean) {

    }
}