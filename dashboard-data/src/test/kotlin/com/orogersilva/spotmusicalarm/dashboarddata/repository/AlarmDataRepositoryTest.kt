package com.orogersilva.spotmusicalarm.dashboarddata.repository

import com.nhaarman.mockitokotlin2.*
import com.orogersilva.spotmusicalarm.dashboarddata.contract.AlarmDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.contract.TrackDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.mapper.ArtistMapper
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Artist
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Track
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.AlarmRepository
import org.junit.Before
import org.junit.Test
import java.util.*

class AlarmDataRepositoryTest {

    // region PROPERTIES

    private lateinit var alarmLocalDataSourceMock: AlarmDataContract.Local
    private lateinit var trackLocalDataSourceMock: TrackDataContract.Local

    private lateinit var alarmRepository: AlarmRepository

    // endregion

    // region SETUP METHOD

    @Before fun setUp() {

        alarmLocalDataSourceMock = mock()
        trackLocalDataSourceMock = mock()

        alarmRepository = AlarmDataRepository(alarmLocalDataSourceMock, trackLocalDataSourceMock)
    }

    // endregion

    // region TEST METHODS

    @Test fun `Save alarm, when track is null, then save alarm without associated track`() {

        // ARRANGE

        val EMITTED_VALUE_COUNT = 1

        val ALARM_DATE_IN_MILLIS = 1591506000000L

        val ALARM_DATE = Date(ALARM_DATE_IN_MILLIS)
        val ALARM_IS_ENABLED = true

        val EXPECTED_ALARM_ID = 1L

        whenever(alarmLocalDataSourceMock.saveAlarm(ALARM_DATE, ALARM_IS_ENABLED, null))
                .thenReturn(EXPECTED_ALARM_ID)

        // ACT

        val testObserver = alarmRepository.saveAlarm(ALARM_DATE, ALARM_IS_ENABLED, null)
                .test()

        // ASSERT

        verifyNoMoreInteractions(trackLocalDataSourceMock)

        testObserver
                .assertNoErrors()
                .assertComplete()
                .assertValueCount(EMITTED_VALUE_COUNT)
                .assertValue { alarmId -> (alarmId == EXPECTED_ALARM_ID) }
    }

    @Test fun `Save alarm, when track is not null, then save alarm with associated track`() {

        // ARRANGE

        val EMITTED_VALUE_COUNT = 1

        val ALARM_DATE_IN_MILLIS = 1591506000000L

        val ALARM_DATE = Date(ALARM_DATE_IN_MILLIS)
        val ALARM_IS_ENABLED = true

        val ARTIST_ID = "1LgVvBvmQm48GExZDH322C"
        val ARTIST_NAME = "Good Knight Productions"

        val TRACK_ID = "325S3FzTRw7jWAWup9n2vF"

        val artist = Artist(ARTIST_ID, ARTIST_NAME, TRACK_ID)

        val artists = listOf(artist)

        val TRACK_NAME = "DKC - Theme"

        val track = Track(TRACK_ID, TRACK_NAME, artists)

        val EXPECTED_ALARM_ID = 1L

        whenever(alarmLocalDataSourceMock.saveAlarm(ALARM_DATE, ALARM_IS_ENABLED, TRACK_ID))
                .thenReturn(EXPECTED_ALARM_ID)

        // ACT

        val testObserver = alarmRepository.saveAlarm(ALARM_DATE, ALARM_IS_ENABLED, track)
                .test()

        // ASSERT

        verify(trackLocalDataSourceMock).saveTrack(TRACK_ID, TRACK_NAME,
                ArtistMapper.transformArtistsToArtistEntities(artists))

        testObserver
                .assertNoErrors()
                .assertComplete()
                .assertValueCount(EMITTED_VALUE_COUNT)
                .assertValue { alarmId -> (alarmId == EXPECTED_ALARM_ID) }
    }

    // endregion
}