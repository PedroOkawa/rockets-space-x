package com.okawa.rockets.utils.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.okawa.rockets.R
import com.okawa.rockets.db.entity.RocketEntity
import kotlinx.android.synthetic.main.adapter_rocket.view.*

class RocketAdapter: BaseAdapter<RocketEntity, RocketAdapter.RocketViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RocketViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_rocket, parent, false)
        return RocketViewHolder(view)
    }

    override fun onBindViewHolder(rocketViewHolder: RocketViewHolder, position: Int) {
        val rocket = getItem(position)
        rocketViewHolder.txtRocketAdapterName.text = rocket.name
        rocketViewHolder.txtRocketAdapterCountry.text = rocket.country
        rocketViewHolder.txtRocketAdapterEngineCount.text = rocket.enginesCount.toString()
        rocketViewHolder.viwRocketAdapterActiveSign.isActivated = rocket.active
        rocketViewHolder.itemView.setOnClickListener {
            onItemClickListener?.onClick(rocket)
        }
        Glide
            .with(rocketViewHolder.imgRocketAdapterImage)
            .load(rocket.images.firstOrNull())
            .into(rocketViewHolder.imgRocketAdapterImage)
    }

    inner class RocketViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val txtRocketAdapterName = view.txtRocketAdapterName
        val txtRocketAdapterCountry = view.txtRocketAdapterCountry
        val txtRocketAdapterEngineCount = view.txtRocketAdapterEngineCount
        val imgRocketAdapterImage = view.imgRocketAdapterImage
        val viwRocketAdapterActiveSign = view.viwRocketAdapterActiveSign

    }
}