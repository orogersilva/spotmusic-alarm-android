package com.orogersilva.spotmusicalarm.dashboarddata.local.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey

@Entity(
        tableName = "artist",
        foreignKeys = [
            ForeignKey(entity = TrackEntity::class,
                    parentColumns = ["id"],
                    childColumns = ["track_id"],
                    onDelete = CASCADE)
        ]
)
data class ArtistEntity(@PrimaryKey val id: String,
                        @ColumnInfo(name = "name") val name: String,
                        @ColumnInfo(name = "track_id") val trackId: String)