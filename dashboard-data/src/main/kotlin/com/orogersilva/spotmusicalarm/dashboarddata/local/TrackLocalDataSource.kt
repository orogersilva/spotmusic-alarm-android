package com.orogersilva.spotmusicalarm.dashboarddata.local

import com.orogersilva.spotmusicalarm.dashboarddata.contract.TrackDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.local.dao.ArtistDAO
import com.orogersilva.spotmusicalarm.dashboarddata.local.dao.TrackDAO
import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.ArtistEntity
import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.TrackEntity
import javax.inject.Inject

class TrackLocalDataSource @Inject constructor(
    private val trackDAO: TrackDAO,
    private val artistDAO: ArtistDAO
) : TrackDataContract.Local {

    // region OVERRIDED METHODS

    override fun saveTrack(id: String, name: String, artistEntities: List<ArtistEntity>) {

        artistDAO.insertArtists(artistEntities)
        trackDAO.insertTrack(TrackEntity(id, name))
    }

    // endregion
}