package com.orogersilva.spotmusicalarm.dashboarddata.di.module

import com.orogersilva.spotmusicalarm.dashboarddata.contract.TrackDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.local.SpotmusicAlarmDashboardDatabase
import com.orogersilva.spotmusicalarm.dashboarddata.local.TrackLocalDataSource
import com.orogersilva.spotmusicalarm.dashboarddata.local.dao.ArtistDAO
import com.orogersilva.spotmusicalarm.dashboarddata.local.dao.TrackDAO
import com.orogersilva.spotmusicalarm.dashboarddomain.di.scope.DashboardScope
import dagger.Module
import dagger.Provides

@Module
open class TrackRepositoryModule {

    // region PROVIDERS

    @Provides @DashboardScope open fun provideTrackDAO(dashboardDatabase: SpotmusicAlarmDashboardDatabase): TrackDAO =
            dashboardDatabase.getTrackDAO()

    @Provides @DashboardScope open fun provideArtistDAO(dashboardDatabase: SpotmusicAlarmDashboardDatabase): ArtistDAO =
            dashboardDatabase.getArtistDAO()

    @Provides @DashboardScope open fun provideTrackLocalDataSource(
            trackDAO: TrackDAO,
            artistDAO: ArtistDAO
    ): TrackDataContract.Local =
            TrackLocalDataSource(trackDAO, artistDAO)

    // endregion
}