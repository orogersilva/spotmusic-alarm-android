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
object PlaylistViewModelModule {

    // region PROVIDERS

    @Provides @ActivityScope @JvmStatic fun providePlaylistViewModel(playlistActivity: PlaylistActivity,
                                                                     playlistRepository: PlaylistRepository,
                                                                     userRepository: UserRepository,
                                                                     schedulerProvider: SchedulerProvider): PlaylistViewModel {

        val playlistViewModelFactory = PlaylistViewModelFactory(
                playlistRepository, userRepository, schedulerProvider
        )

        return ViewModelProviders.of(playlistActivity, playlistViewModelFactory)
                .get(PlaylistViewModel::class.java)
    }

    @Provides @ActivityScope @JvmStatic fun providePlaylistAdapter(playlistViewModel: PlaylistViewModel): PlaylistAdapter =
            PlaylistAdapter(playlistViewModel)

    // endregion
}