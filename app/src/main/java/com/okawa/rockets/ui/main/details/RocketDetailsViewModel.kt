package com.okawa.rockets.ui.main.details

import android.arch.lifecycle.*
import com.okawa.rockets.data.Result
import com.okawa.rockets.db.entity.LaunchEntity
import com.okawa.rockets.db.entity.RocketEntity
import com.okawa.rockets.repository.launch.LaunchRepository
import com.okawa.rockets.repository.rocket.RocketRepository
import javax.inject.Inject

class RocketDetailsViewModel @Inject constructor(
    private val launchRepository: LaunchRepository,
    private val rocketRepository: RocketRepository
): ViewModel() {

    private val rocketIdLiveData = MutableLiveData<String>()
    private val rocketLiveData: LiveData<Result<RocketEntity>>
    private val launchLiveData: LiveData<Result<List<LaunchEntity>>>

    init {
        rocketLiveData = Transformations.switchMap(rocketIdLiveData) { rocketId ->
            rocketRepository.getRocket(rocketId)
        }

        launchLiveData = Transformations.switchMap(rocketIdLiveData) { rocketId ->
            launchRepository.getLaunchesByRocketId(rocketId)
        }
    }

    fun defineRocketId(rocketId: String?) {
        rocketIdLiveData.value = rocketId
    }

    fun getRocketLiveData() = rocketLiveData

    fun getLaunchLiveData() = launchLiveData

    fun retrieveRocketIdValue() = rocketIdLiveData.value

}