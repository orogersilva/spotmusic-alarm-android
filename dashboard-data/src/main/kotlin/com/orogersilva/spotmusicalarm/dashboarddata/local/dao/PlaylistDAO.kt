package com.orogersilva.spotmusicalarm.dashboarddata.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.PlaylistEntity
import io.reactivex.Single

@Dao
interface PlaylistDAO {

    // region CRUD

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertPlaylist(playlistEntity: PlaylistEntity): Long

    @Query("SELECT * FROM playlist WHERE id = :id")
    fun getPlaylist(id: String): Single<PlaylistEntity>

    // endregion
}