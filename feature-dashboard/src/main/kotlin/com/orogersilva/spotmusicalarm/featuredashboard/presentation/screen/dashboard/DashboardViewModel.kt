package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.dashboard

import android.arch.lifecycle.ViewModel
import com.orogersilva.spotmusicalarm.featuredashboard.shared.SingleLiveEvent

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