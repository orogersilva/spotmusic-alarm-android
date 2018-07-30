package com.orogersilva.spotmusicalarm.dashboarddata.local.typeconverter

import android.arch.persistence.room.TypeConverter
import java.util.*

object DateTypeConverter {

    // region CONVERTERS

    @TypeConverter @JvmStatic fun fromTimestamp(value: Long?): Date? =
            if (value == null) null else Date(value)

    @TypeConverter @JvmStatic fun dateToTimestamp(date: Date?): Long? =
            date?.time

    // endregion
}