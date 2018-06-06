package com.orogersilva.spotmusicalarm.core

import android.arch.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    // region PROPERTIES

    val newClockAlarmEvent = SingleLiveEvent<Void>()

    // endregion

    // region PUBLIC METHODS

    fun createClockAlarm() {

        newClockAlarmEvent.call()
    }

    // endregion
}