package com.orogersilva.spotmusicalarm.dashboarddata.contract

import java.util.* // ktlint-disable no-wildcard-imports

interface AlarmDataContract {

    // region INTERFACES

    interface Local {

        // region METHODS

        fun saveAlarm(
            dateTime: Date,
            isEnabled: Boolean,
            trackId: String?
        ): Long

        // endregion
    }

    // endregion
}