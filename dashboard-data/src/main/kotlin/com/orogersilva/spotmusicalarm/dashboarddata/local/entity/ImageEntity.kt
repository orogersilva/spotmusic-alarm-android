package com.orogersilva.spotmusicalarm.dashboarddata.local.entity

import android.arch.persistence.room.*
import android.arch.persistence.room.ForeignKey.CASCADE

@Entity(
        tableName = "image",
        foreignKeys = [
            ForeignKey(entity = PlaylistEntity::class,
                    parentColumns = ["id"],
                    childColumns = ["playlist_id"],
                    onDelete = CASCADE)
        ],
        indices = [
            Index(value = ["playlist_id"])
        ]
)
data class ImageEntity(
        @PrimaryKey(autoGenerate = true) val id: Long,
        @ColumnInfo(name = "url") val url: String,
        @ColumnInfo(name = "width") val width: Int?,
        @ColumnInfo(name = "height") val height: Int?,
        @ColumnInfo(name = "playlist_id") val playlistId: String
)