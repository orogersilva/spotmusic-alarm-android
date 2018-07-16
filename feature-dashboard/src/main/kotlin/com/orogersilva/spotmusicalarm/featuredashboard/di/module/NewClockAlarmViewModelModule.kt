package com.orogersilva.spotmusicalarm.featuredashboard.di.module

import android.arch.lifecycle.ViewModelProviders
import com.orogersilva.spotmusicalarm.base.di.scope.ActivityScope
import com.orogersilva.spotmusicalarm.base.scheduler.SchedulerProvider
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.UserRepository
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.newclockalarm.NewClockAlarmViewModel
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.newclockalarm.NewClockAlarmViewModelFactory
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.newclockalarm.view.NewClockAlarmActivity
import dagger.Module
import dagger.Provides

@Module
object NewClockAlarmViewModelModule {

    // region PROVIDERS

    @Provides @ActivityScope @JvmStatic fun provideNewClockAlarmViewModel(newClockAlarmActivity: NewClockAlarmActivity,
                                                                          userRepository: UserRepository,
                                                                          schedulerProvider: SchedulerProvider): NewClockAlarmViewModel {

        val newClockAlarmViewModelFactory = NewClockAlarmViewModelFactory(userRepository, schedulerProvider)

        return ViewModelProviders.of(newClockAlarmActivity, newClockAlarmViewModelFactory)
                .get(NewClockAlarmViewModel::class.java)
    }

    // endregion
}