package com.orogersilva.spotmusicalarm.featuredashboard.di.component

import com.orogersilva.spotmusicalarm.base.di.scope.ActivityScope
import com.orogersilva.spotmusicalarm.dashboarddata.di.module.PlaylistDataSourceModule
import com.orogersilva.spotmusicalarm.featuredashboard.di.module.PlaylistViewModelModule
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.playlist.view.PlaylistActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
        modules = [
            PlaylistDataSourceModule::class,
            PlaylistViewModelModule::class
        ]
)
interface PlaylistViewComponent {

    // region INJECTORS

    fun inject(playlistActivity: PlaylistActivity)

    // endregion
}