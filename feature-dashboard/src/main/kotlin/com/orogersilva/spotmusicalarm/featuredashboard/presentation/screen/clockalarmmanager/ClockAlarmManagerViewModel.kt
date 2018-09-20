package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.clockalarmmanager

import android.arch.lifecycle.ViewModel
import com.orogersilva.spotmusicalarm.dashboarddata.shared.SingleLiveEvent

class ClockAlarmManagerViewModel : ViewModel() {

    // region PROPERTIES

    val newClockAlarmLiveData = SingleLiveEvent<Void>()

    // endregion

    // region PUBLIC METHODS

    fun resume() {


    }

    fun createClockAlarm() {

        newClockAlarmLiveData.call()
    }

    // endregion
}