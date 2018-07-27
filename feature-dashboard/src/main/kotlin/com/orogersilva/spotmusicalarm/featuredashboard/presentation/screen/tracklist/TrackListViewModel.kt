package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.tracklist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.orogersilva.spotmusicalarm.base.scheduler.SchedulerProvider
import com.orogersilva.spotmusicalarm.dashboarddata.factory.TrackDataSourceFactory
import com.orogersilva.spotmusicalarm.dashboarddata.shared.SingleLiveEvent
import com.orogersilva.spotmusicalarm.dashboarddomain.enums.NetworkState
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Track
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.TrackRepository
import javax.inject.Inject

class TrackListViewModel @Inject constructor(private val trackDataSourceFactory: TrackDataSourceFactory) : ViewModel() {

    // region PROPERTIES

    val initialLoadingLiveData: LiveData<NetworkState>
    val networkStateLiveData: LiveData<NetworkState>
    val trackPagedListLiveData: LiveData<PagedList<Track>>

    val selectedTrackSingleEvent = SingleLiveEvent<String>()

    // endregion

    // region INITIALIZER BLOCK

    init {

        initialLoadingLiveData = Transformations.switchMap(trackDataSourceFactory.trackPaginationDataSourceLiveData) {
            trackPaginationDataSource -> trackPaginationDataSource.initialLoadingLiveData
        }

        networkStateLiveData = Transformations.switchMap(trackDataSourceFactory.trackPaginationDataSourceLiveData) {
            trackPaginationDataSource -> trackPaginationDataSource.networkStateLiveData
        }

        val trackPagedPagingConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(15)
                .setInitialLoadSizeHint(20)
                .build()

        trackPagedListLiveData = LivePagedListBuilder(trackDataSourceFactory, trackPagedPagingConfig)
                .build()
    }

    // endregion

    // region PUBLIC METHODS

    fun selectTrack(id: String) {

        selectedTrackSingleEvent.value = id
    }

    // endregion
}