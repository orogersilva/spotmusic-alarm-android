package com.orogersilva.spotmusicalarm.dashboarddata.local.entity

/* ktlint-disable no-wildcard-imports */
import android.arch.persistence.room.*
import java.util.*
/* ktlint-enable no-wildcard-imports */

@Entity(tableName = "alarm",
        foreignKeys = [
            (ForeignKey(entity = TrackEntity::class,
        parentColumns = ["id"],
        childColumns = ["track_id"],
        onDelete = ForeignKey.SET_NULL))
        ],
        indices = [
            (Index(value = ["track_id"]))
        ]
)
data class AlarmEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "date_time") val dateTime: Date,
    @ColumnInfo(name = "is_enabled") val isEnabled: Boolean,
    @ColumnInfo(name = "track_id") val trackId: String?
)