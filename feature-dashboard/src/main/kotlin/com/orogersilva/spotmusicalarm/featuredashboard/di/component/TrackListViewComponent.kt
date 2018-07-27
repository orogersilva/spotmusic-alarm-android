package com.orogersilva.spotmusicalarm.featuredashboard.di.component

import com.orogersilva.spotmusicalarm.base.di.scope.ActivityScope
import com.orogersilva.spotmusicalarm.dashboarddata.di.module.PlaylistDataSourceModule
import com.orogersilva.spotmusicalarm.dashboarddata.di.module.TrackDataSourceModule
import com.orogersilva.spotmusicalarm.featuredashboard.di.module.TrackListViewModelModule
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.tracklist.view.TrackListActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
        modules = [
            TrackDataSourceModule::class,
            TrackListViewModelModule::class
        ]
)
interface TrackListViewComponent {

    // region INJECTORS

    fun inject(trackListActivity: TrackListActivity)

    // endregion
}