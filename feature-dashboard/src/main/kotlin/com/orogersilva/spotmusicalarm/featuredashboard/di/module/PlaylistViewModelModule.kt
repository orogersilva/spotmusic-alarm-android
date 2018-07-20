package com.orogersilva.spotmusicalarm.featuredashboard.di.module

import android.arch.lifecycle.ViewModelProviders
import com.orogersilva.spotmusicalarm.base.di.scope.ActivityScope
import com.orogersilva.spotmusicalarm.base.scheduler.SchedulerProvider
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.PlaylistRepository
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.UserRepository
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.playlist.PlaylistViewModel
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.playlist.PlaylistViewModelFactory
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.playlist.adapter.PlaylistAdapter
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.playlist.view.PlaylistActivity
import dagger.Module
import dagger.Provides

@Module
open class PlaylistViewModelModule(private val playlistActivity: PlaylistActivity) {

    // region PROVIDERS

    @Provides @ActivityScope open fun providePlaylistViewModel(playlistRepository: PlaylistRepository,
                                                               userRepository: UserRepository,
                                                               schedulerProvider: SchedulerProvider): PlaylistViewModel {

        val playlistViewModelFactory = PlaylistViewModelFactory(
                playlistRepository, userRepository, schedulerProvider
        )

        return ViewModelProviders.of(playlistActivity, playlistViewModelFactory)
                .get(PlaylistViewModel::class.java)
    }

    @Provides @ActivityScope open fun providePlaylistAdapter(playlistViewModel: PlaylistViewModel): PlaylistAdapter =
            PlaylistAdapter(playlistViewModel)

    // endregion
}