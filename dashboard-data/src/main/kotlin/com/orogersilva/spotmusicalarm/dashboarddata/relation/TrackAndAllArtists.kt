package com.orogersilva.spotmusicalarm.dashboarddata.relation

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation
import com.orogersilva.spotmusicalarm.dashboarddata.entity.ArtistEntity
import com.orogersilva.spotmusicalarm.dashboarddata.entity.TrackEntity

class TrackAndAllArtists {

    // region RELATIONSHIPS

    @Embedded var trackEntity: TrackEntity? = null

    @Relation(parentColumn = "id", entityColumn = "track_id") var artistEntities = mutableListOf<ArtistEntity>()

    // endregion
}