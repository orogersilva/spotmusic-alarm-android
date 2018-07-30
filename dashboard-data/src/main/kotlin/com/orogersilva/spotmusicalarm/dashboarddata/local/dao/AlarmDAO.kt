package com.orogersilva.spotmusicalarm.dashboarddata.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.AlarmEntity

@Dao
interface AlarmDAO {

    // region CRUD

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertAlarm(alarmEntity: AlarmEntity): Long

    // endregion
}