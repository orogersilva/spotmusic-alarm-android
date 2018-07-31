package com.orogersilva.spotmusicalarm.dashboarddomain.repository

import com.orogersilva.spotmusicalarm.dashboarddomain.model.Track
import java.util.*

interface AlarmRepository {

    // region METHODS

    fun saveAlarm(dateTime: Date, isEnabled: Boolean, track: Track?)

    // endregion
}