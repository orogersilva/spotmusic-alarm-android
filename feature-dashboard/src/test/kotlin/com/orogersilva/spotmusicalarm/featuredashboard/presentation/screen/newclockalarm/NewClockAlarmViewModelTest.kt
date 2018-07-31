package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.newclockalarm

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.orogersilva.spotmusicalarm.base.scheduler.SchedulerProvider
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Artist
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Track
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.AlarmRepository
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.UserRepository
import com.orogersilva.spotmusicalarm.testutils.scheduler.impl.TestSchedulerProvider
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class NewClockAlarmViewModelTest {

    // region PROPERTIES

    private lateinit var alarmRepositoryMock: AlarmRepository
    private lateinit var userRepositoryMock: UserRepository
    private lateinit var schedulerProvider: SchedulerProvider

    private lateinit var newClockAlarmViewModel: NewClockAlarmViewModel

    @JvmField @Rule val instantTaskExecutorRule = InstantTaskExecutorRule()

    // endregion

    // region SETUP METHOD

    @Before fun setUp() {

        alarmRepositoryMock = mock()
        userRepositoryMock = mock()
        schedulerProvider = TestSchedulerProvider()

        newClockAlarmViewModel = NewClockAlarmViewModel(alarmRepositoryMock,
                userRepositoryMock, schedulerProvider)
    }

    // endregion

    // region TEST METHODS

    @Test fun `Save access token, when access token is provided, then verify repository layer is called`() {

        // ARRANGE

        val ACCESS_TOKEN = "k3dKssoamxMa20dj3Lzamk1La9ssamxLDfjsKE3d9dmxMxnzSKie20da0sk2"

        // ACT

        newClockAlarmViewModel.saveAccessToken(ACCESS_TOKEN)

        // ASSERT

        verify(userRepositoryMock).saveAccessToken(ACCESS_TOKEN)
    }

    @Test fun `Save clock alarm settings, when clock alarm info is provided, then pass alarm id for clock alarm manager screen`() {

        // ARRANGE

        val ALARM_DATE_IN_MILLIS = 1591506000000L

        val ALARM_DATE = Date(ALARM_DATE_IN_MILLIS)
        val ALARM_IS_ENABLED = true

        val TRACK_ID = "325S3FzTRw7jWAWup9n2vF"

        val ARTIST_ID = "1LgVvBvmQm48GExZDH322C"
        val ARTIST_NAME = "Good Knight Productions"

        val artist = Artist(ARTIST_ID, ARTIST_NAME, TRACK_ID)

        val artists = listOf(artist)

        val TRACK_NAME = "DKC - Theme"

        val track = Track(TRACK_ID, TRACK_NAME, artists)

        val EXPECTED_ALARM_ID = 1L

        val expectedAlarmIdData = Single.just(EXPECTED_ALARM_ID)

        whenever(alarmRepositoryMock.saveAlarm(ALARM_DATE, ALARM_IS_ENABLED, track))
                .thenReturn(expectedAlarmIdData)

        // ACT

        newClockAlarmViewModel.saveClockAlarmSettings(ALARM_DATE, ALARM_IS_ENABLED, track)

        // ASSERT

        assertEquals(EXPECTED_ALARM_ID, newClockAlarmViewModel.alarmIdMutableLiveData.value)
    }

    // endregion
}