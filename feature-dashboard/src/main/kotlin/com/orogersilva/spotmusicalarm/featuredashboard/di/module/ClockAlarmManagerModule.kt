package com.orogersilva.spotmusicalarm.featuredashboard.di.module

import android.arch.lifecycle.ViewModelProviders
import com.orogersilva.spotmusicalarm.base.di.scope.ActivityScope
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.clockalarmmanager.ClockAlarmManagerViewModel
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.clockalarmmanager.ClockAlarmManagerViewModelFactory
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.clockalarmmanager.adapter.ClockAlarmManagerListAdapter
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.clockalarmmanager.view.ClockAlarmManagerActivity
import dagger.Module
import dagger.Provides

@Module
object ClockAlarmManagerModule {

    // region PROVIDERS

    @Provides @ActivityScope @JvmStatic fun provideClockAlarmManagerViewModel(clockAlarmManagerActivity: ClockAlarmManagerActivity): ClockAlarmManagerViewModel {

        val dashboardViewModelFactory = ClockAlarmManagerViewModelFactory()

        return ViewModelProviders.of(clockAlarmManagerActivity, dashboardViewModelFactory)
                .get(ClockAlarmManagerViewModel::class.java)
    }

    @Provides @ActivityScope @JvmStatic fun provideDashboardAdapter(clockAlarmManagerViewModel: ClockAlarmManagerViewModel): ClockAlarmManagerListAdapter =
            ClockAlarmManagerListAdapter(clockAlarmManagerViewModel)

    // endregion
}