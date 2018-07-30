package com.orogersilva.spotmusicalarm.device.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.PowerManager

class AlarmManagerBroadcastReceiver : BroadcastReceiver() {

    // region PROPERTIES

    private val REPEATABLE_TIMER = "repeatable_timer"

    // endregion

    // region PUBLIC METHODS

    fun setRepeatableAlarm(context: Context) {

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, AlarmManagerBroadcastReceiver::class.java)

        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
                1000 * 5, pendingIntent)
    }

    fun cancelRepeatableAlarm(context: Context) {

        val intent = Intent(context, AlarmManagerBroadcastReceiver::class.java)

        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmManager.cancel(pendingIntent)
    }

    // endregion

    // region OVERRIDED METHODS

    override fun onReceive(context: Context, intent: Intent) {

        val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager

        val powerManagerWakeLock = powerManager.newWakeLock(
                PowerManager.PARTIAL_WAKE_LOCK,
                "AlarmManagerBroadcastReceiver"
        )

        val WAKE_LOCK_TIMEOUT = 10000L

        powerManagerWakeLock.acquire(WAKE_LOCK_TIMEOUT)

        powerManagerWakeLock.release()
    }

    // endregion
}