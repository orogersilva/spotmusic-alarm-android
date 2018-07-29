package com.orogersilva.spotmusicalarm.dashboarddata.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.orogersilva.spotmusicalarm.dashboarddata.BuildConfig
import com.orogersilva.spotmusicalarm.dashboarddata.local.dao.PlaylistDAO
import com.orogersilva.spotmusicalarm.dashboarddata.local.dao.TrackDAO
import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.*

@Database(entities = [
    ArtistEntity::class,
    ImageEntity::class,
    PlaylistEntity::class,
    TrackEntity::class,
    UserEntity::class
], version = BuildConfig.DASHBOARD_DATABASE_VERSION)
abstract class SpotmusicAlarmDashboardDatabase : RoomDatabase() {

    // region DAO's

    abstract fun getPlaylistDAO(): PlaylistDAO

    abstract fun getTrackDAO(): TrackDAO

    // endregion
}