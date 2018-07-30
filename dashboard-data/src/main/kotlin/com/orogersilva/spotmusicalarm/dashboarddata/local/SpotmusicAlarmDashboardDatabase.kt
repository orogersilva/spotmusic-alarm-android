package com.orogersilva.spotmusicalarm.dashboarddata.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.orogersilva.spotmusicalarm.dashboarddata.BuildConfig
import com.orogersilva.spotmusicalarm.dashboarddata.local.dao.PlaylistDAO
import com.orogersilva.spotmusicalarm.dashboarddata.local.dao.TrackDAO
import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.*
import com.orogersilva.spotmusicalarm.dashboarddata.local.typeconverter.DateTypeConverter

@Database(entities = [
    ArtistEntity::class,
    ImageEntity::class,
    PlaylistEntity::class,
    TrackEntity::class,
    UserEntity::class
], version = BuildConfig.DASHBOARD_DATABASE_VERSION)
@TypeConverters(DateTypeConverter::class)
abstract class SpotmusicAlarmDashboardDatabase : RoomDatabase() {

    // region DAO's

    abstract fun getPlaylistDAO(): PlaylistDAO

    abstract fun getTrackDAO(): TrackDAO

    // endregion
}