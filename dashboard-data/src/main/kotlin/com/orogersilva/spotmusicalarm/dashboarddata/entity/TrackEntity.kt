package com.orogersilva.spotmusicalarm.dashboarddata.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey

@Entity(
        tableName = "track",
        foreignKeys = [
            ForeignKey(entity = PlaylistEntity::class,
                    parentColumns = ["id"],
                    childColumns = ["playlist_id"],
                    onDelete = CASCADE)
        ]
)
data class TrackEntity(@PrimaryKey val id: Int,
                       @ColumnInfo(name = "name") val name: String,
                       @ColumnInfo(name = "preview_url") val previewUrl: String,
                       @ColumnInfo(name = "playlist_id") val playlistId: String)