package com.orogersilva.spotmusicalarm.dashboarddata.contract

import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.ArtistEntity

interface TrackDataContract {

    // region INTERFACES

    interface Local {

        // region METHODS

        fun saveTrack(id: String, name: String, artistEntities: List<ArtistEntity>)

        // endregion
    }

    // endregion
}