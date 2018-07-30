package com.orogersilva.spotmusicalarm.dashboarddomain.repository

import java.util.*

interface AlarmRepository {

    // region METHODS

    fun saveAlarm(dateTime: Date, isEnabled: Boolean, trackId: String?)

    // endregion
}