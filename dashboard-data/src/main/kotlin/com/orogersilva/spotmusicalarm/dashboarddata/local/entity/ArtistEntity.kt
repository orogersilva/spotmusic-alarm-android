package com.orogersilva.spotmusicalarm.dashboarddata.local.entity

import android.arch.persistence.room.*
import android.arch.persistence.room.ForeignKey.CASCADE

@Entity(
        tableName = "artist",
        foreignKeys = [
            ForeignKey(entity = TrackEntity::class,
                    parentColumns = ["id"],
                    childColumns = ["track_id"],
                    onDelete = CASCADE)
        ],
        indices = [
            Index(value = ["track_id"])
        ]
)
data class ArtistEntity(
        @PrimaryKey val id: String,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "track_id") val trackId: String
)