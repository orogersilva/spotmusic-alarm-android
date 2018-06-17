package com.orogersilva.spotmusicalarm.core.di.module

import android.arch.lifecycle.ViewModelProviders
import com.orogersilva.spotmusicalarm.base.di.scope.ActivityScope
import com.orogersilva.spotmusicalarm.core.presentation.screen.newclockalarm.NewClockAlarmViewModel
import com.orogersilva.spotmusicalarm.core.presentation.screen.newclockalarm.NewClockAlarmViewModelFactory
import com.orogersilva.spotmusicalarm.core.presentation.screen.newclockalarm.view.NewClockAlarmActivity
import dagger.Module
import dagger.Provides

@Module
object NewClockAlarmModule {

    // region PROVIDERS

    @Provides @ActivityScope @JvmStatic fun provideNewClockAlarmViewModel(newClockAlarmActivity: NewClockAlarmActivity): NewClockAlarmViewModel {

        val newClockAlarmViewModelFactory = NewClockAlarmViewModelFactory()

        return ViewModelProviders.of(newClockAlarmActivity, newClockAlarmViewModelFactory)
                .get(NewClockAlarmViewModel::class.java)
    }

    // endregion
}