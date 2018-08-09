package com.orogersilva.spotmusicalarm.dashboarddata.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.TrackEntity
import io.reactivex.Maybe

@Dao
interface TrackDAO {

    // region CRUD

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertTrack(trackEntity: TrackEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertTracks(trackEntities: List<TrackEntity>): List<Long>

    @Query("SELECT * FROM track WHERE id = :id")
    fun getTrack(id: String): Maybe<TrackEntity>

    @Query("SELECT * FROM track")
    fun getTracks(): Maybe<List<TrackEntity>>

    // endregion
}