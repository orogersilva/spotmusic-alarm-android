package com.orogersilva.spotmusicalarm.dashboarddata.di.module

import com.orogersilva.spotmusicalarm.base.di.scope.ActivityScope
import com.orogersilva.spotmusicalarm.base.scheduler.SchedulerProvider
import com.orogersilva.spotmusicalarm.dashboarddata.contract.PlaylistDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.contract.UserDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.factory.TrackDataSourceFactory
import com.orogersilva.spotmusicalarm.dashboarddata.pagination.TrackPaginationDataSource
import dagger.Module
import dagger.Provides

@Module
open class TrackDataSourceModule(private val playlistId: String) {

    // region PROVIDERS

    @Provides @ActivityScope open fun provideTrackPaginationDataSource(
            playlistRemoteDataSource: PlaylistDataContract.Remote,
            userRemoteDataSource: UserDataContract.Remote,
            schedulerProvider: SchedulerProvider
    ): TrackPaginationDataSource =
            TrackPaginationDataSource(playlistRemoteDataSource, userRemoteDataSource, schedulerProvider, playlistId)

    @Provides @ActivityScope open fun provideTrackDataSourceFactory(trackPaginationDataSource: TrackPaginationDataSource): TrackDataSourceFactory =
            TrackDataSourceFactory(trackPaginationDataSource)

    // endregion
}