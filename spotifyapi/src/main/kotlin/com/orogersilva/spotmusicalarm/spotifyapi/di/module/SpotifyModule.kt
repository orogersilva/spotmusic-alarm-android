package com.orogersilva.spotmusicalarm.spotifyapi.di.module

import android.support.v7.app.AppCompatActivity
import com.orogersilva.spotmusicalarm.base.di.scope.ModuleScope
import com.orogersilva.spotmusicalarm.spotifyapi.SpotifyAdapterHelper
import com.orogersilva.spotmusicalarm.spotifyapi.SpotifyWrapper
import dagger.Module
import dagger.Provides

@Module
object SpotifyModule {

    // region PROVIDERS

    @Provides @ModuleScope @JvmStatic fun provideSpotifyAdapterHelper(): SpotifyAdapterHelper =
            SpotifyAdapterHelper(SpotifyWrapper())

    // endregion
}