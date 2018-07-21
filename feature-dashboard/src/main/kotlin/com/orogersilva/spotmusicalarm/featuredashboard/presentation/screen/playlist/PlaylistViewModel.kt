package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.playlist

import android.arch.lifecycle.ViewModel
import com.orogersilva.spotmusicalarm.base.scheduler.SchedulerProvider
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.PlaylistRepository
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.UserRepository
import com.orogersilva.spotmusicalarm.featuredashboard.shared.SingleLiveEvent
import javax.inject.Inject

class PlaylistViewModel @Inject constructor(private val playlistRepository: PlaylistRepository,
                                            private val userRepository: UserRepository,
                                            private val schedulerProvider: SchedulerProvider) : ViewModel() {

    // region PROPERTIES

    val selectedPlaylistEvent = SingleLiveEvent<String>()
    val isShowingPlaylistsLoadingIndicator = SingleLiveEvent<Boolean>()

    // endregion

    // region INITIALIZER BLOCK

    init {

        isShowingPlaylistsLoadingIndicator.value = true
    }

    // endregion

    // region PUBLIC METHODS

    fun resume() {

        loadPlaylists()
    }

    fun selectPlaylist(id: String) {

        selectedPlaylistEvent.value = id
    }

    fun loadPlaylists() {

        userRepository.getMe()
                .flatMap { user -> playlistRepository.getPagedUserPlaylists(user.id) }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ user ->

                    var i = 1

                    i++

                }, {

                    var i = 1

                    i++
                })
    }

    // endregion
}