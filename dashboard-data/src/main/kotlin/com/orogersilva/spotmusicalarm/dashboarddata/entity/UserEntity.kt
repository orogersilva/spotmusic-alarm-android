package com.orogersilva.spotmusicalarm.dashboarddata.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(@PrimaryKey val id: String,
                      @ColumnInfo(name = "display_name") val displayName: String?)