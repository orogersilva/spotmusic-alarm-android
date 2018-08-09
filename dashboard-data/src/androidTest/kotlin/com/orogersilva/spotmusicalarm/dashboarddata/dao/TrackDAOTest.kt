package com.orogersilva.spotmusicalarm.dashboarddata.dao

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.orogersilva.spotmusicalarm.dashboarddata.local.SpotmusicAlarmDashboardDatabase
import com.orogersilva.spotmusicalarm.dashboarddata.local.dao.PlaylistDAO
import com.orogersilva.spotmusicalarm.dashboarddata.local.dao.TrackDAO
import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.TrackEntity
import org.hamcrest.Matchers.`is`
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TrackDAOTest {

    // region PROPERTIES

    private lateinit var dashboardDatabase: SpotmusicAlarmDashboardDatabase

    private lateinit var playlistDAO: PlaylistDAO
    private lateinit var trackDAO: TrackDAO

    @JvmField @Rule val instantTaskExecutorRule = InstantTaskExecutorRule()

    // endregion

    // region SETUP METHOD

    @Before fun setUp() {

        dashboardDatabase = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                SpotmusicAlarmDashboardDatabase::class.java
        ).allowMainThreadQueries()
                .build()

        playlistDAO = dashboardDatabase.getPlaylistDAO()
        trackDAO = dashboardDatabase.getTrackDAO()
    }

    // endregion

    // region TEST METHODS

    @Test fun insertTrackWhenASingleTrackIsInsertedOnDbThenItIsPersistedSuccessfully() {

        // ARRANGE

        val EXPECTED_INSERTED_PLAYLIST_ROW_ID = 1L
        val EXPECTED_INSERTED_TRACK_ROW_ID = 1L

        val TRACK_ID = "325S3FzTRw7jWAWup9n2vF"
        val TRACK_NAME = "DKC - Theme"

        val trackEntity = TrackEntity(TRACK_ID, TRACK_NAME)

        // ACT

        val insertedTrackRowId = trackDAO.insertTrack(trackEntity)

        // ASSERT

        assertThat(insertedTrackRowId, `is`(EXPECTED_INSERTED_TRACK_ROW_ID))
    }

    @Test fun insertTrackWhenASingleTrackIsInsertedTwiceThenItMustBePersistedOnceSuccessfully() {

        // ARRANGE

        val EXPECTED_INSERTED_PLAYLIST_ROW_ID = 1L
        val EXPECTED_INSERTED_TRACK_ROW_ID = 1L
        val EXPECTED_SAME_INSERTED_TRACK_BY_SECOND_TIME_ROW_ID = 2L

        val EMITTED_VALUE_COUNT = 1

        val TRACK_ID = "325S3FzTRw7jWAWup9n2vF"
        val TRACK_NAME = "DKC - Theme"

        val trackEntity = TrackEntity(TRACK_ID, TRACK_NAME)

        // ACT / ASSERT

        var insertedTrackRowId = trackDAO.insertTrack(trackEntity)

        assertThat(insertedTrackRowId, `is`(EXPECTED_INSERTED_TRACK_ROW_ID))

        insertedTrackRowId = trackDAO.insertTrack(trackEntity)

        // ASSERT

        assertThat(insertedTrackRowId, `is`(EXPECTED_SAME_INSERTED_TRACK_BY_SECOND_TIME_ROW_ID))

        val testObserver = trackDAO.getTracks().test()

        testObserver
                .assertNoErrors()
                .assertComplete()
                .assertValueCount(EMITTED_VALUE_COUNT)
                .assertValue { trackEntities -> (trackEntities.size == 1) }
    }

    @Test fun insertTracksWhenTrackListIsEmptyThenNoneTrackIsInsertedOnDb() {

        // ARRANGE

        val expectedTrackEntities = listOf<TrackEntity>()

        // ACT

        val rowIds = trackDAO.insertTracks(expectedTrackEntities)

        // ASSERT

        assertTrue(rowIds.isEmpty())
    }

    @Test fun insertTracksWhenThereAreTracksToBeInsertedThenTheseTracksArePersistedOnDb() {

        // ARRANGE

        val TRACK_1_ROW_ID = 1L
        val TRACK_2_ROW_ID = 2L
        val TRACK_3_ROW_ID = 3L

        val expectedRowIds = listOf(TRACK_1_ROW_ID, TRACK_2_ROW_ID, TRACK_3_ROW_ID)

        // TRACK 1

        val TRACK_ID_1 = "325S3FzTRw7jWAWup9n2vF"
        val TRACK_NAME_1 = "DKC - Theme"

        val trackEntity1 = TrackEntity(TRACK_ID_1, TRACK_NAME_1)

        // TRACK 2

        val TRACK_ID_2 = "0oI62zS46c7zIHMj7BJyBv"
        val TRACK_NAME_2 = "Donkey Kong Country Vol.1"

        val trackEntity2 = TrackEntity(TRACK_ID_2, TRACK_NAME_2)

        // TRACK 3

        val TRACK_ID_3 = "1KGjO8rHK7LaJ2QSrpZlPF"
        val TRACK_NAME_3 = "DKC - Island Swing"

        val trackEntity3 = TrackEntity(TRACK_ID_3, TRACK_NAME_3)

        val expectedTrackEntities = listOf(trackEntity1, trackEntity2, trackEntity3)

        // ACT

        val rowIds = trackDAO.insertTracks(expectedTrackEntities)

        // ASSERT

        assertTrue(expectedRowIds.toTypedArray() contentEquals rowIds.toTypedArray())
    }

    @Test fun getTrackWhenThereIsNotTrackOnDbThenNoneTrackIsRetrieved() {

        // ARRANGE

        val TRACK_ID = "325S3FzTRw7jWAWup9n2vF"

        // ACT

        val testObserver = trackDAO.getTrack(TRACK_ID).test()

        // ASSERT

        testObserver
                .assertNoErrors()
                .assertComplete()
                .assertNoValues()
    }

    @Test fun getTrackWhenThereIsTrackWithWantedIdOnDbThenTrackIsRetrieved() {

        // ARRANGE

        val EMITTED_VALUE_COUNT = 1

        val TRACK_ID = "325S3FzTRw7jWAWup9n2vF"
        val TRACK_NAME = "DKC - Theme"

        val expectedTrackEntity = TrackEntity(TRACK_ID, TRACK_NAME)

        trackDAO.insertTrack(expectedTrackEntity)

        // ACT

        val testObserver = trackDAO.getTrack(TRACK_ID).test()

        // ASSERT

        testObserver
                .assertNoErrors()
                .assertComplete()
                .assertValueCount(EMITTED_VALUE_COUNT)
                .assertValue { trackEntity -> (trackEntity == expectedTrackEntity) }
    }

    @Test fun getTracksWhenThereIsNotTracksOnDbThenNoneTrackIsRetrieved() {

        // ARRANGE

        val EMITTED_VALUE_COUNT = 1

        // ARRANGE / ACT

        val testObserver = trackDAO.getTracks().test()

        // ASSERT

        testObserver
                .assertNoErrors()
                .assertComplete()
                .assertValueCount(EMITTED_VALUE_COUNT)
                .assertValue { trackEntities -> (trackEntities.size == 0) }
    }

    @Test fun getTracksWhenThereIsTracksOnDbThenReturnAllTracks() {

        // ARRANGE

        val EMITTED_VALUE_COUNT = 1

        // TRACK 1

        val TRACK_ID_1 = "325S3FzTRw7jWAWup9n2vF"
        val TRACK_NAME_1 = "DKC - Theme"

        val trackEntity1 = TrackEntity(TRACK_ID_1, TRACK_NAME_1)

        // TRACK 2

        val TRACK_ID_2 = "0oI62zS46c7zIHMj7BJyBv"
        val TRACK_NAME_2 = "Donkey Kong Country Vol.1"

        val trackEntity2 = TrackEntity(TRACK_ID_2, TRACK_NAME_2)

        // TRACK 3

        val TRACK_ID_3 = "1KGjO8rHK7LaJ2QSrpZlPF"
        val TRACK_NAME_3 = "DKC - Island Swing"

        val trackEntity3 = TrackEntity(TRACK_ID_3, TRACK_NAME_3)

        val expectedTrackEntities = listOf(trackEntity1, trackEntity2, trackEntity3)

        trackDAO.insertTracks(expectedTrackEntities)

        // ACT

        val testObserver = trackDAO.getTracks().test()

        // ASSERT

        testObserver
                .assertNoErrors()
                .assertComplete()
                .assertValueCount(EMITTED_VALUE_COUNT)
                .assertValue { trackEntities ->
                    trackEntities.toTypedArray() contentEquals expectedTrackEntities.toTypedArray()
                }
    }

    // endregion

    // region TEARDOWN METHOD

    @After fun tearDown() {

        dashboardDatabase.close()
    }

    // endregion
}