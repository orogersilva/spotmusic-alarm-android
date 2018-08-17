package com.orogersilva.spotmusicalarm.dashboarddata.local

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.orogersilva.spotmusicalarm.dashboarddata.contract.TrackDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.local.dao.ArtistDAO
import com.orogersilva.spotmusicalarm.dashboarddata.local.dao.TrackDAO
import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.TrackEntity
import com.orogersilva.spotmusicalarm.dashboarddata.mapper.ArtistMapper
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Artist
import org.junit.Before
import org.junit.Test

class TrackLocalDataSourceTest {

    // region PROPERTIES

    private lateinit var trackDAOMock: TrackDAO
    private lateinit var artistDAOMock: ArtistDAO

    private lateinit var trackLocalDataSource: TrackDataContract.Local

    // endregion

    // region SETUP METHOD

    @Before fun setUp() {

        trackDAOMock = mock()
        artistDAOMock = mock()

        trackLocalDataSource = TrackLocalDataSource(trackDAOMock, artistDAOMock)
    }

    // endregion

    // region TEST METHODS

    @Test fun `Save track, when track info is provided, then call artist's and track's persistence layer successfully`() {

        // ARRANGE

        val ARTIST_ID = "1LgVvBvmQm48GExZDH322C"
        val ARTIST_NAME = "Good Knight Productions"

        val TRACK_ID = "325S3FzTRw7jWAWup9n2vF"

        val artist = Artist(ARTIST_ID, ARTIST_NAME, TRACK_ID)

        val TRACK_NAME = "DKC - Theme"

        val trackEntity = TrackEntity(TRACK_ID, TRACK_NAME)
        val artistEntities = ArtistMapper.transformArtistsToArtistEntities(listOf(artist))

        // ACT

        trackLocalDataSource.saveTrack(TRACK_ID, TRACK_NAME, artistEntities)

        // ASSERT

        verify(artistDAOMock).insertArtists(artistEntities)
        verify(trackDAOMock).insertTrack(trackEntity)
    }

    // endregion
}