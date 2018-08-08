package com.orogersilva.spotmusicalarm.dashboarddata.local.entity

import android.arch.persistence.room.*
import com.orogersilva.spotmusicalarm.dashboarddata.local.typeconverter.DateTypeConverter
import java.util.*

@Entity(tableName = "alarm",
        foreignKeys = [
            ForeignKey(entity = TrackEntity::class,
                    parentColumns = ["id"],
                    childColumns = ["track_id"],
                    onDelete = ForeignKey.SET_NULL)
        ],
        indices = [
            Index(value = ["track_id"])
        ]
)
data class AlarmEntity(@PrimaryKey(autoGenerate = true) val id: Long = 0L,
                       @ColumnInfo(name = "date_time") val dateTime: Date,
                       @ColumnInfo(name = "is_enabled") val isEnabled: Boolean,
                       @ColumnInfo(name = "track_id") val trackId: String?)