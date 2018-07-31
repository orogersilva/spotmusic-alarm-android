package com.orogersilva.spotmusicalarm.dashboarddata.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.orogersilva.spotmusicalarm.dashboarddata.BuildConfig
import com.orogersilva.spotmusicalarm.dashboarddata.local.SpotmusicAlarmDashboardDatabase
import com.orogersilva.spotmusicalarm.dashboarddomain.di.scope.DashboardScope
import dagger.Module
import dagger.Provides

@Module
open class DashboardDatabaseModule {

    // region PROVIDERS

    @Provides @DashboardScope open fun provideDashboardDatabase(context: Context): SpotmusicAlarmDashboardDatabase =
            Room.databaseBuilder(context, SpotmusicAlarmDashboardDatabase::class.java,
                    BuildConfig.DASHBOARD_DATABASE_NAME).build()

    // endregion
}