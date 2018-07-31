package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.tracklist

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.orogersilva.spotmusicalarm.base.scheduler.SchedulerProvider
import com.orogersilva.spotmusicalarm.dashboarddata.factory.TrackDataSourceFactory
import javax.inject.Inject

class TrackListViewModelFactory @Inject constructor(private val trackDataSourceFactory: TrackDataSourceFactory)
    : ViewModelProvider.NewInstanceFactory() {

    // region OVERRIDED METHODS

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            TrackListViewModel(trackDataSourceFactory) as T

    // endregion
}