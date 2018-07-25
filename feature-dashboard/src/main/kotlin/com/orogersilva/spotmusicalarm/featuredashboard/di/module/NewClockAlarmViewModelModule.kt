package com.orogersilva.spotmusicalarm.featuredashboard.di.module

import android.arch.lifecycle.ViewModelProviders
import com.orogersilva.spotmusicalarm.base.di.scope.ActivityScope
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.UserRepository
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.newclockalarm.NewClockAlarmViewModel
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.newclockalarm.NewClockAlarmViewModelFactory
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.newclockalarm.view.NewClockAlarmActivity
import dagger.Module
import dagger.Provides

@Module
open class NewClockAlarmViewModelModule(private val newClockAlarmActivity: NewClockAlarmActivity) {

    // region PROVIDERS

    @Provides @ActivityScope open fun provideNewClockAlarmViewModel(userRepository: UserRepository): NewClockAlarmViewModel {

        val newClockAlarmViewModelFactory = NewClockAlarmViewModelFactory(userRepository)

        return ViewModelProviders.of(newClockAlarmActivity, newClockAlarmViewModelFactory)
                .get(NewClockAlarmViewModel::class.java)
    }

    // endregion
}