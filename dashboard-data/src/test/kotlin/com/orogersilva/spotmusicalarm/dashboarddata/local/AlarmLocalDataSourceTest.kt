package com.orogersilva.spotmusicalarm.dashboarddata.local

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.orogersilva.spotmusicalarm.dashboarddata.contract.AlarmDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.local.dao.AlarmDAO
import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.AlarmEntity
import org.junit.Before
import org.junit.Test
import java.util.*

class AlarmLocalDataSourceTest {

    // region PROPERTIES

    private lateinit var alarmDAOMock: AlarmDAO

    private lateinit var alarmLocalDataSource: AlarmDataContract.Local

    // endregion

    // region SETUP METHOD

    @Before fun setUp() {

        alarmDAOMock = mock()

        alarmLocalDataSource = AlarmLocalDataSource(alarmDAOMock)
    }

    // endregion

    // region TEST METHODS

    @Test fun `Save alarm, when alarm info is provided, then call persistence layer successfully`() {

        // ARRANGE

        val ALARM_DATE_IN_MILLIS = 1591506000000L

        val ALARM_DATE = Date(ALARM_DATE_IN_MILLIS)
        val ALARM_IS_ENABLED = true
        val TRACK_ID = "325S3FzTRw7jWAWup9n2vF"

        val expectedAlarmEntity = AlarmEntity(dateTime = ALARM_DATE,
                isEnabled = ALARM_IS_ENABLED, trackId = TRACK_ID)

        // ACT

        alarmLocalDataSource.saveAlarm(ALARM_DATE, ALARM_IS_ENABLED, TRACK_ID)

        // ASSERT

        verify(alarmDAOMock).insertAlarm(expectedAlarmEntity)
    }

    // endregion
}