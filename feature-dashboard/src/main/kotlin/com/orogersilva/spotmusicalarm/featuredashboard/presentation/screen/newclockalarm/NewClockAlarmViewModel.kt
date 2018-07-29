package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.newclockalarm

import android.arch.lifecycle.ViewModel
import com.orogersilva.spotmusicalarm.dashboarddata.shared.SingleLiveEvent
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.UserRepository
import javax.inject.Inject

class NewClockAlarmViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    // region PROPERTIES

    val clockAlarmTimeConfigEvent = SingleLiveEvent<Void>()
    val clockAlarmMusicConfigEvent = SingleLiveEvent<Void>()
    val definedClockAlarmConfigEvent = SingleLiveEvent<Void>()

    // endregion

    // region PUBLIC METHODS

    fun resume() {
    }

    fun onClockAlarmTimeConfigEvent() {

        clockAlarmTimeConfigEvent.call()
    }

    fun onClockAlarmMusicConfigEvent() {

        clockAlarmMusicConfigEvent.call()
    }

    fun onDefinedClockAlarmConfigEvent() {

        definedClockAlarmConfigEvent.call()
    }


    fun saveClockAlarmSettings() {


    }

    fun saveAccessToken(accessToken: String) {

        userRepository.saveAccessToken(accessToken)
    }

    // endregion
}