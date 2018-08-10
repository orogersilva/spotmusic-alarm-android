package com.orogersilva.spotmusicalarm.dashboarddata.local

import com.orogersilva.spotmusicalarm.dashboarddata.contract.AlarmDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.local.dao.AlarmDAO
import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.AlarmEntity
import java.util.* // ktlint-disable no-wildcard-imports
import javax.inject.Inject

class AlarmLocalDataSource @Inject constructor(private val alarmDAO: AlarmDAO) : AlarmDataContract.Local {

    // region OVERRIDED METHODS

    override fun saveAlarm(dateTime: Date, isEnabled: Boolean, trackId: String?): Long =
            alarmDAO.insertAlarm(
                    AlarmEntity(
                            dateTime = dateTime,
                            isEnabled = isEnabled,
                            trackId = trackId)
            )

    // endregion
}