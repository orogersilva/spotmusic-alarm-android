package com.orogersilva.spotmusicalarm.featuredashboard.di.component

import com.orogersilva.spotmusicalarm.base.di.component.ApplicationComponent
import com.orogersilva.spotmusicalarm.base.di.scope.ActivityScope
import com.orogersilva.spotmusicalarm.dashboarddata.di.module.PlaylistRepositoryModule
import com.orogersilva.spotmusicalarm.base.di.module.PreferencesModule
import com.orogersilva.spotmusicalarm.dashboarddata.di.module.UserRepositoryModule
import com.orogersilva.spotmusicalarm.featuredashboard.di.module.PlaylistViewModelModule
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.playlist.view.PlaylistActivity
import dagger.Component
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
        modules = [
            PlaylistRepositoryModule::class,
            PlaylistViewModelModule::class
        ]
)
interface PlaylistViewComponent {

    // region INJECTORS

    fun inject(playlistActivity: PlaylistActivity)

    // endregion
}