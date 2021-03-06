package com.orogersilva.spotmusicalarm.featuredashboard.di.component

import com.orogersilva.spotmusicalarm.base.di.component.ApplicationComponent
import com.orogersilva.spotmusicalarm.dashboarddata.di.module.*
import com.orogersilva.spotmusicalarm.dashboarddomain.di.scope.DashboardScope
import com.orogersilva.spotmusicalarm.featuredashboard.di.module.ClockAlarmManagerViewModelModule
import com.orogersilva.spotmusicalarm.featuredashboard.di.module.NewClockAlarmViewModelModule
import com.orogersilva.spotmusicalarm.featuredashboard.di.module.PlaylistViewModelModule
import com.orogersilva.spotmusicalarm.featuredashboard.di.module.TrackListViewModelModule
import com.orogersilva.spotmusicalarm.spotifyapi.di.module.SpotifyModule
import dagger.Component

@DashboardScope
@Component(
        modules = [
            AlarmRepositoryModule::class,
            DashboardDatabaseModule::class,
            PlaylistDataSourceModule::class,
            TrackRepositoryModule::class,
            UserRepositoryModule::class
        ],
        dependencies = [ApplicationComponent::class]
)
interface DashboardComponent {

    // region FACTORY METHODS

    fun plusClockAlarmManagerViewComponent(clockAlarmManagerViewModule: ClockAlarmManagerViewModelModule): ClockAlarmManagerViewComponent

    fun plusNewClockAlarmViewComponent(newClockAlarmViewModelModule: NewClockAlarmViewModelModule,
                                       spotifyModule: SpotifyModule): NewClockAlarmViewComponent

    fun plusPlaylistViewComponent(playlistViewModelModule: PlaylistViewModelModule): PlaylistViewComponent

    fun plusTrackListViewComponent(trackDataSourceModule: TrackDataSourceModule,
                                   trackListViewModelModule: TrackListViewModelModule): TrackListViewComponent

    // endregion
}