package com.okawa.rockets.ui.main.rocketslist

import android.widget.Toast
import com.okawa.rockets.R
import com.okawa.rockets.ui.base.BaseFragment

class RocketsListFragment: BaseFragment() {

    override fun layoutToInflate() = R.layout.fragment_rockets_list

    override fun doOnCreated() {
        Toast.makeText(context, "MAIN ACTIVITY", Toast.LENGTH_SHORT).show()
    }
}