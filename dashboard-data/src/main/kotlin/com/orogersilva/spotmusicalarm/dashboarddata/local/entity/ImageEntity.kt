package com.orogersilva.spotmusicalarm.dashboarddata.local.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey

@Entity(
        tableName = "image",
        foreignKeys = [
            ForeignKey(entity = PlaylistEntity::class,
                    parentColumns = ["id"],
                    childColumns = ["playlist_id"],
                    onDelete = CASCADE)
        ]
)
data class ImageEntity(@PrimaryKey(autoGenerate = true) val id: Long,
                       @ColumnInfo(name = "url") val url: String,
                       @ColumnInfo(name = "width") val width: Int?,
                       @ColumnInfo(name = "height") val height: Int?,
                       @ColumnInfo(name = "playlist_id") val playlistId: String)