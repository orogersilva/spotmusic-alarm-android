package com.orogersilva.spotmusicalarm.spotifyapi.di.module

import com.orogersilva.spotmusicalarm.base.di.scope.ActivityScope
import com.orogersilva.spotmusicalarm.spotifyapi.SpotifyAdapterHelper
import com.orogersilva.spotmusicalarm.spotifyapi.SpotifyWrapper
import dagger.Module
import dagger.Provides

@Module
object SpotifyModule {

    // region PROVIDERS

    @Provides @ActivityScope @JvmStatic fun provideSpotifyAdapterHelper(): SpotifyAdapterHelper =
            SpotifyAdapterHelper(SpotifyWrapper())

    // endregion
}