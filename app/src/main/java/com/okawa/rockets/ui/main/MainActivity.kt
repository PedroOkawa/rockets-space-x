package com.okawa.rockets.ui.main

import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.okawa.rockets.R
import com.okawa.rockets.ui.base.BaseActivity

class MainActivity: BaseActivity() {

    override fun layoutToInflate() = R.layout.activity_main

    override fun doOnCreated() {
        initActionBar()
    }

    private fun initActionBar() {
        val navController = findNavController(R.id.frmMainActivityContent)
        setupActionBarWithNavController(navController)
    }
}