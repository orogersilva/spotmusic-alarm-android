package com.orogersilva.spotmusicalarm.base

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