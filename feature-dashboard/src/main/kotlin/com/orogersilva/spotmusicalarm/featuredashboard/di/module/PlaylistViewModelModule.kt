package com.orogersilva.spotmusicalarm.featuredashboard.di.module

import android.arch.lifecycle.ViewModelProviders
import com.orogersilva.spotmusicalarm.base.di.scope.ActivityScope
import com.orogersilva.spotmusicalarm.dashboarddata.factory.PlaylistDataSourceFactory
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.playlist.PlaylistViewModel
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.playlist.PlaylistViewModelFactory
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.playlist.adapter.PlaylistPagedAdapter
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.playlist.view.PlaylistActivity
import dagger.Module
import dagger.Provides

@Module
open class PlaylistViewModelModule(private val playlistActivity: PlaylistActivity) {

    // region PROVIDERS

    @Provides @ActivityScope open fun providePlaylistViewModel(playlistDataSourceFactory: PlaylistDataSourceFactory): PlaylistViewModel {

        val playlistViewModelFactory = PlaylistViewModelFactory(playlistDataSourceFactory)

        return ViewModelProviders.of(playlistActivity, playlistViewModelFactory)
                .get(PlaylistViewModel::class.java)
    }

    @Provides @ActivityScope open fun providePlaylistPagedAdapter(playlistViewModel: PlaylistViewModel): PlaylistPagedAdapter =
            PlaylistPagedAdapter(playlistViewModel)

    // endregion
}