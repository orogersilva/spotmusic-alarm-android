package com.orogersilva.spotmusicalarm.dashboarddata.dao

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.orogersilva.spotmusicalarm.dashboarddata.local.SpotmusicAlarmDashboardDatabase
import com.orogersilva.spotmusicalarm.dashboarddata.local.dao.PlaylistDAO
import com.orogersilva.spotmusicalarm.dashboarddata.local.dao.TrackDAO
import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.PlaylistEntity
import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.TrackEntity
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.hamcrest.Matchers.`is`
import org.junit.After
import org.junit.Assert.assertThat
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

        val EMITTED_VALUE_COUNT = 1

        val PLAYLIST_ID = "0HM6i5SUouetTNY9dslhtC"
        val PLAYLIST_NAME = "Donkey Kong Country"

        val playlistEntity = PlaylistEntity(PLAYLIST_ID, PLAYLIST_NAME)

        val insertedPlaylistRowId = playlistDAO.insertPlaylist(playlistEntity)

        val TRACK_ID = "325S3FzTRw7jWAWup9n2vF"
        val TRACK_NAME = "DKC - Theme"

        val trackEntity = TrackEntity(TRACK_ID, TRACK_NAME, PLAYLIST_ID)

        // ACT

        val insertedTrackRowId = trackDAO.insertTrack(trackEntity)

        // ASSERT

        assertThat(insertedPlaylistRowId, `is`(EXPECTED_INSERTED_PLAYLIST_ROW_ID))
        assertThat(insertedTrackRowId, `is`(EXPECTED_INSERTED_TRACK_ROW_ID))

        val testObserver = TestObserver<TrackEntity>()

        trackDAO.getTrack(trackEntity.id)
                .subscribe(testObserver)

        testObserver
                .assertNoErrors()
                .assertComplete()
                .assertValueCount(EMITTED_VALUE_COUNT)
                .assertOf { retrievedTrackEntity ->
                    (retrievedTrackEntity == Single.just(trackEntity))
                }
    }

    @Test fun insertTrackWhenASingleTrackIsInsertedTwiceThenItMustBePersistedOnceSuccessfully() {

        // ARRANGE

        val EXPECTED_INSERTED_PLAYLIST_ROW_ID = 1L
        val EXPECTED_INSERTED_TRACK_ROW_ID = 1L
        val EXPECTED_SAME_INSERTED_TRACK_BY_SECOND_TIME_ROW_ID = 2L

        val EMITTED_VALUE_COUNT = 1

        val PLAYLIST_ID = "0HM6i5SUouetTNY9dslhtC"
        val PLAYLIST_NAME = "Donkey Kong Country"

        val playlistEntity = PlaylistEntity(PLAYLIST_ID, PLAYLIST_NAME)

        val insertedPlaylistRowId = playlistDAO.insertPlaylist(playlistEntity)

        val TRACK_ID = "325S3FzTRw7jWAWup9n2vF"
        val TRACK_NAME = "DKC - Theme"

        val trackEntity = TrackEntity(TRACK_ID, TRACK_NAME, PLAYLIST_ID)

        // ACT / ASSERT

        var insertedTrackRowId = trackDAO.insertTrack(trackEntity)

        assertThat(insertedTrackRowId, `is`(EXPECTED_INSERTED_TRACK_ROW_ID))

        insertedTrackRowId = trackDAO.insertTrack(trackEntity)

        // ASSERT

        assertThat(insertedPlaylistRowId, `is`(EXPECTED_INSERTED_PLAYLIST_ROW_ID))
        assertThat(insertedTrackRowId, `is`(EXPECTED_SAME_INSERTED_TRACK_BY_SECOND_TIME_ROW_ID))

        trackDAO.getTracks()
                .test()
                .assertValueCount(EMITTED_VALUE_COUNT)
                .assertOf { retrievedTrackEntity ->
                    (retrievedTrackEntity == Flowable.just(trackEntity))
                }
    }

    // endregion

    // region TEARDOWN METHOD

    @After fun tearDown() {

        dashboardDatabase.close()
    }

    // endregion
}