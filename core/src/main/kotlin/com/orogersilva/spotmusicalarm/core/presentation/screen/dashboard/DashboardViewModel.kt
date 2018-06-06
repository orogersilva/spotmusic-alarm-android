package com.orogersilva.spotmusicalarm.core.presentation.screen.dashboard

import android.arch.lifecycle.ViewModel
import com.orogersilva.spotmusicalarm.core.shared.SingleLiveEvent

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