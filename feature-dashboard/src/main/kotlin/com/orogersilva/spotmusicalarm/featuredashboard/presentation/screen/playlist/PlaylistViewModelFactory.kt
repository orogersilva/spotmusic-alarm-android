package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.playlist

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.orogersilva.spotmusicalarm.base.scheduler.SchedulerProvider
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.PlaylistRepository
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.UserRepository
import javax.inject.Inject

class PlaylistViewModelFactory @Inject constructor(private val playlistRepository: PlaylistRepository,
                                                   private val userRepository: UserRepository,
                                                   private val schedulerProvider: SchedulerProvider) : ViewModelProvider.NewInstanceFactory() {

    // region OVERRIDED METHODS

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            PlaylistViewModel(playlistRepository, userRepository, schedulerProvider) as T

    // endregion
}