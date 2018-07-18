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
object ClockAlarmManagerViewModelModule {

    // region PROVIDERS

    @Provides @ActivityScope @JvmStatic fun provideClockAlarmManagerViewModel(clockAlarmManagerActivity: ClockAlarmManagerActivity): ClockAlarmManagerViewModel {

        val clockAlarmManagerViewModelFactory = ClockAlarmManagerViewModelFactory()

        return ViewModelProviders.of(clockAlarmManagerActivity, clockAlarmManagerViewModelFactory)
                .get(ClockAlarmManagerViewModel::class.java)
    }

    @Provides @ActivityScope @JvmStatic fun provideDashboardAdapter(): ClockAlarmManagerListAdapter =
            ClockAlarmManagerListAdapter()

    // endregion
}