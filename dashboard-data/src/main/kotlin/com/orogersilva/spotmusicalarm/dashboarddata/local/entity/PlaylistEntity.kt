package com.orogersilva.spotmusicalarm.dashboarddata.local.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "playlist")
data class PlaylistEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String
)