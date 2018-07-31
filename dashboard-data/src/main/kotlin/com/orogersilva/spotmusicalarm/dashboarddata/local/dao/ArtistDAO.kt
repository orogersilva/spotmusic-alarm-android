package com.orogersilva.spotmusicalarm.dashboarddata.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.ArtistEntity

@Dao
interface ArtistDAO {

    // region CRUD

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertArtists(artistEntities: List<ArtistEntity>): List<Long>

    // endregion
}