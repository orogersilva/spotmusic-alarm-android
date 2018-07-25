package com.orogersilva.spotmusicalarm.featuredashboard.di.component

import com.orogersilva.spotmusicalarm.base.di.scope.ActivityScope
import com.orogersilva.spotmusicalarm.featuredashboard.di.module.NewClockAlarmViewModelModule
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.newclockalarm.view.NewClockAlarmActivity
import com.orogersilva.spotmusicalarm.spotifyapi.di.module.SpotifyModule
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
        modules = [
            NewClockAlarmViewModelModule::class,
            SpotifyModule::class
        ]
)
interface NewClockAlarmViewComponent {

    // region INJECTORS

    fun inject(newClockAlarmActivity: NewClockAlarmActivity)

    // endregion
}