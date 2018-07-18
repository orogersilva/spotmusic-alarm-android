package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.newclockalarm

import android.arch.lifecycle.ViewModel
import com.orogersilva.spotmusicalarm.base.scheduler.SchedulerProvider
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.UserRepository
import com.orogersilva.spotmusicalarm.featuredashboard.shared.SingleLiveEvent
import javax.inject.Inject

class NewClockAlarmViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    // region PROPERTIES

    val setClockAlarmMusicEvent = SingleLiveEvent<Void>()

    // endregion

    // region PUBLIC METHODS

    fun setClockAlarmTime() {


    }

    fun setClockAlarmMusic() {

        setClockAlarmMusicEvent.call()
    }

    fun saveClockAlarmSettings() {


    }

    fun saveAccessToken(accessToken: String) {

        userRepository.saveAccessToken(accessToken)
    }

    // endregion
}