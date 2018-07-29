package com.orogersilva.spotmusicalarm.dashboarddata.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.TrackEntity
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface TrackDAO {

    // region CRUD

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertTrack(trackEntity: TrackEntity): Long

    @Query("SELECT * FROM track WHERE id = :id")
    fun getTrack(id: String): Single<TrackEntity>

    @Query("SELECT * FROM track")
    fun getTracks(): Flowable<TrackEntity>

    // endregion
}