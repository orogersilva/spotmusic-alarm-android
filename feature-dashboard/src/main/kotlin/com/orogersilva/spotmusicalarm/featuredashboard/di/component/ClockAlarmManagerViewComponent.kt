package com.orogersilva.spotmusicalarm.featuredashboard.di.component

import com.orogersilva.spotmusicalarm.base.di.component.ApplicationComponent
import com.orogersilva.spotmusicalarm.base.di.scope.ActivityScope
import com.orogersilva.spotmusicalarm.featuredashboard.di.module.ClockAlarmManagerViewModelModule
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.clockalarmmanager.view.ClockAlarmManagerActivity
import com.orogersilva.spotmusicalarm.spotifyapi.di.module.SpotifyModule
import dagger.Component
import dagger.android.AndroidInjector

@ActivityScope
@Component(
        modules = [
            ClockAlarmManagerViewModelModule::class,
            SpotifyModule::class
        ],
        dependencies = [ApplicationComponent::class]
)
interface ClockAlarmManagerViewComponent : AndroidInjector<ClockAlarmManagerActivity> {

    // region BUILDERS

    @Component.Builder abstract class Builder : AndroidInjector.Builder<ClockAlarmManagerActivity>() {

        abstract operator fun plus(component: ApplicationComponent): Builder

        fun inject(activity: ClockAlarmManagerActivity) {

            plus(activity.provideApplication().applicationComponent)

            create(activity)
                    .inject(activity)
        }
    }

    // endregion
}