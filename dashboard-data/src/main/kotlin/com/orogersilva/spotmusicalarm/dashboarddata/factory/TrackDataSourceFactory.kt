package com.orogersilva.spotmusicalarm.dashboarddata.factory

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.orogersilva.spotmusicalarm.dashboarddata.pagination.TrackPaginationDataSource
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Track
import javax.inject.Inject

class TrackDataSourceFactory @Inject constructor(private val trackPaginationDataSource: TrackPaginationDataSource)
    : DataSource.Factory<Long, Track>() {

    // region PROPERTIES

    val trackPaginationDataSourceLiveData = MutableLiveData<TrackPaginationDataSource>()

    // endregion

    // region OVERRIDED METHODS

    override fun create(): DataSource<Long, Track> {

        trackPaginationDataSourceLiveData.postValue(trackPaginationDataSource)

        return trackPaginationDataSource
    }

    // endregion
}