package com.okawa.rockets.ui.main.list

import android.arch.lifecycle.ViewModel
import android.util.Log
import com.okawa.rockets.repository.RocketRepository
import javax.inject.Inject

class RocketsListViewModel @Inject constructor(
    private val rocketRepository: RocketRepository
): ViewModel() {

    fun retrieveRockets() = rocketRepository.getRockets()

}