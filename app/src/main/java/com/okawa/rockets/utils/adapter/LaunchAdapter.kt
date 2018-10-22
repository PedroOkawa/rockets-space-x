package com.okawa.rockets.utils.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okawa.rockets.R
import com.okawa.rockets.db.entity.LaunchEntity
import kotlinx.android.synthetic.main.adapter_launch.view.*

class LaunchAdapter: BaseAdapter<LaunchEntity, LaunchAdapter.LaunchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): LaunchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_launch, parent, false)
        return LaunchViewHolder(view)
    }

    override fun onBindViewHolder(launchViewHolder: LaunchViewHolder, position: Int) {
        val launch = getItem(position)
        launchViewHolder.txtLaunchAdapterName.text = launch.rocketId
    }

    inner class LaunchViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val txtLaunchAdapterName = view.txtLaunchAdapterName

    }
}