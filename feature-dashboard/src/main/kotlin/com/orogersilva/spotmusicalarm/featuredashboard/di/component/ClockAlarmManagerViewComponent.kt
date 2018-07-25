package com.orogersilva.spotmusicalarm.featuredashboard.di.component

import com.orogersilva.spotmusicalarm.base.di.scope.ActivityScope
import com.orogersilva.spotmusicalarm.featuredashboard.di.module.ClockAlarmManagerViewModelModule
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.clockalarmmanager.view.ClockAlarmManagerActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
        modules = [
            ClockAlarmManagerViewModelModule::class
        ]
)
interface ClockAlarmManagerViewComponent {

    // region INJECTORS

    fun inject(clockAlarmManagerActivity: ClockAlarmManagerActivity)

    // endregion
}