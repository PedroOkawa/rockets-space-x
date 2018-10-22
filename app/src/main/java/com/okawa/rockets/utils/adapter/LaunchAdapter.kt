package com.okawa.rockets.utils.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okawa.rockets.R
import com.okawa.rockets.db.entity.LaunchEntity
import com.okawa.rockets.utils.DateUtils
import com.okawa.rockets.utils.extensions.setTextBoolean
import com.okawa.rockets.utils.extensions.setTextDate
import com.okawa.rockets.utils.extensions.setTextYear
import com.okawa.rockets.utils.module.GlideApp
import kotlinx.android.synthetic.main.adapter_launch.view.*

class LaunchAdapter constructor(private val dateUtils: DateUtils): BaseAdapter<LaunchEntity, LaunchAdapter.LaunchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): LaunchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_launch, parent, false)
        return LaunchViewHolder(view)
    }

    override fun onBindViewHolder(launchViewHolder: LaunchViewHolder, position: Int) {
        val launch = getItem(position)

        defineHeader(launchViewHolder, position)

        launchViewHolder.txtLaunchAdapterMissionName.text = launch.missionName
        launchViewHolder.txtLaunchAdapterLaunchDate.setTextDate(launch.launchDate)
        launchViewHolder.txtLaunchAdapterSuccess.setTextBoolean(R.string.launch_adapter_success_text, launch.launchSuccess)

        GlideApp
            .with(launchViewHolder.imgLaunchAdapterPatch)
            .load(launch.missionPatch)
            .placeholder(R.drawable.ic_image_placeholder)
            .into(launchViewHolder.imgLaunchAdapterPatch)
    }

    private fun defineHeader(launchViewHolder: LaunchViewHolder, position: Int) {
        val launchEntity = getItem(position)
        if(position == 0) {
            launchViewHolder.txtLaunchAdapterHeader.visibility = View.VISIBLE
            launchViewHolder.txtLaunchAdapterHeader.setTextYear(launchEntity.launchDate)
        } else if(position > 0) {
            val previousLaunchEntity = getItem(position - 1)
            if(!dateUtils.checkSameYear(previousLaunchEntity.launchDate, launchEntity.launchDate)) {
                launchViewHolder.txtLaunchAdapterHeader.visibility = View.VISIBLE
                launchViewHolder.txtLaunchAdapterHeader.setTextYear(launchEntity.launchDate)
            }
        }

    }

    inner class LaunchViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val txtLaunchAdapterHeader = view.txtLaunchAdapterHeader
        val txtLaunchAdapterMissionName = view.txtLaunchAdapterMissionName
        val txtLaunchAdapterLaunchDate = view.txtLaunchAdapterLaunchDate
        val txtLaunchAdapterSuccess = view.txtLaunchAdapterSuccess
        val imgLaunchAdapterPatch = view.imgLaunchAdapterPatch

    }
}