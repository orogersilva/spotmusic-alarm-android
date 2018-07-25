package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.playlist

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.orogersilva.spotmusicalarm.base.scheduler.SchedulerProvider
import com.orogersilva.spotmusicalarm.dashboarddata.factory.PlaylistDataSourceFactory
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.PlaylistRepository
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.UserRepository
import javax.inject.Inject

class PlaylistViewModelFactory @Inject constructor(private val playlistDataSourceFactory: PlaylistDataSourceFactory)
    : ViewModelProvider.NewInstanceFactory() {

    // region OVERRIDED METHODS

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            PlaylistViewModel(playlistDataSourceFactory) as T

    // endregion
}