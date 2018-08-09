package com.orogersilva.spotmusicalarm.dashboarddomain.repository

import com.orogersilva.spotmusicalarm.dashboarddomain.model.Track
import io.reactivex.Single
import java.util.*

interface AlarmRepository {

    // region METHODS

    fun saveAlarm(
            dateTime: Date,
            isEnabled: Boolean,
            track: Track?
    ): Single<Long>

    // endregion
}