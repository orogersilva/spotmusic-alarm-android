package com.orogersilva.spotmusicalarm.dashboarddata.di.module

import com.orogersilva.spotmusicalarm.dashboarddata.contract.AlarmDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.contract.TrackDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.local.AlarmLocalDataSource
import com.orogersilva.spotmusicalarm.dashboarddata.local.SpotmusicAlarmDashboardDatabase
import com.orogersilva.spotmusicalarm.dashboarddata.local.dao.AlarmDAO
import com.orogersilva.spotmusicalarm.dashboarddata.repository.AlarmDataRepository
import com.orogersilva.spotmusicalarm.dashboarddomain.di.scope.DashboardScope
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.AlarmRepository
import dagger.Module
import dagger.Provides

@Module
open class AlarmRepositoryModule {

    // region PROVIDERS

    @Provides @DashboardScope open fun provideAlarmDAO(dashboardDatabase: SpotmusicAlarmDashboardDatabase): AlarmDAO =
            dashboardDatabase.getAlarmDAO()

    @Provides @DashboardScope open fun provideAlarmLocalDataSource(alarmDAO: AlarmDAO): AlarmDataContract.Local =
            AlarmLocalDataSource(alarmDAO)

    @Provides @DashboardScope open fun provideAlarmRepository(
        alarmLocalDataSource: AlarmDataContract.Local,
        trackLocalDataSource: TrackDataContract.Local
    ): AlarmRepository =
            AlarmDataRepository(alarmLocalDataSource, trackLocalDataSource)

    // endregion
}