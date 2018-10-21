package com.okawa.rockets.ui.main.list

import android.arch.lifecycle.*
import android.arch.paging.PagedList
import com.okawa.rockets.data.Result
import com.okawa.rockets.db.entity.RocketEntity
import com.okawa.rockets.repository.RocketRepository
import javax.inject.Inject

class RocketsListViewModel @Inject constructor(
    private val rocketRepository: RocketRepository
): ViewModel() {

    private val filterLiveData = MutableLiveData<Boolean>()
    private val rocketsLiveData: LiveData<Result<PagedList<RocketEntity>>>

    init {
        rocketsLiveData = Transformations.switchMap(filterLiveData) { filterByActive ->
            rocketRepository.getRockets(filterByActive)
        }
    }

    fun filterByActive(filter: Boolean?) {
        filterLiveData.value = filter
    }

    fun getRocketsLiveData() = rocketsLiveData

    fun retrieveFilterValue() = filterLiveData.value

}