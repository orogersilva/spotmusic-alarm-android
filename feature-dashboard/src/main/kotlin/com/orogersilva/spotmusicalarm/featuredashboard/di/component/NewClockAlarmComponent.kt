package com.orogersilva.spotmusicalarm.featuredashboard.di.component

import com.orogersilva.spotmusicalarm.base.di.component.ApplicationComponent
import com.orogersilva.spotmusicalarm.base.di.module.SchedulerProviderModule
import com.orogersilva.spotmusicalarm.base.di.scope.ActivityScope
import com.orogersilva.spotmusicalarm.dashboarddata.di.module.PreferencesModule
import com.orogersilva.spotmusicalarm.dashboarddata.di.module.UserRepositoryModule
import com.orogersilva.spotmusicalarm.featuredashboard.di.module.NewClockAlarmViewModelModule
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.newclockalarm.view.NewClockAlarmActivity
import com.orogersilva.spotmusicalarm.spotifyapi.di.module.SpotifyModule
import dagger.Component
import dagger.android.AndroidInjector

@ActivityScope
@Component(
        modules = [
            NewClockAlarmViewModelModule::class,
            PreferencesModule::class,
            SchedulerProviderModule::class,
            SpotifyModule::class,
            UserRepositoryModule::class
        ],
        dependencies = [ApplicationComponent::class]
)
interface NewClockAlarmComponent : AndroidInjector<NewClockAlarmActivity> {

    // region BUILDERS

    @Component.Builder abstract class Builder : AndroidInjector.Builder<NewClockAlarmActivity>() {

        abstract operator fun plus(component: ApplicationComponent): Builder

        fun inject(activity: NewClockAlarmActivity) {

            plus(activity.provideApplication().applicationComponent)

            create(activity)
                    .inject(activity)
        }
    }

    // endregion
}