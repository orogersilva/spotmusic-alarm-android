package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.newclockalarm

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.orogersilva.spotmusicalarm.base.scheduler.SchedulerProvider
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.AlarmRepository
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.UserRepository
import javax.inject.Inject

class NewClockAlarmViewModelFactory @Inject constructor(private val alarmRepository: AlarmRepository,
                                                        private val userRepository: UserRepository,
                                                        private val schedulerProvider: SchedulerProvider) : ViewModelProvider.NewInstanceFactory() {

    // region OVERRIDED METHODS

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            NewClockAlarmViewModel(alarmRepository, userRepository, schedulerProvider) as T

    // endregion
}