package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.clockalarmmanager

import android.arch.lifecycle.ViewModel
import com.orogersilva.spotmusicalarm.featuredashboard.shared.SingleLiveEvent

class ClockAlarmManagerViewModel : ViewModel() {

    // region PROPERTIES

    val newClockAlarmEvent = SingleLiveEvent<Void>()

    // endregion

    // region PUBLIC METHODS

    fun resume() {


    }

    fun createClockAlarm() {

        newClockAlarmEvent.call()
    }

    // endregion
}