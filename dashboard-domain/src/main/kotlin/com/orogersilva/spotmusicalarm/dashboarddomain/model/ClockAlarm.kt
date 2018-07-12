package com.orogersilva.spotmusicalarm.dashboarddomain.model

data class ClockAlarm(val id: Long,
                      val trackName: String,
                      val artistName: String,
                      val isEnabled: Boolean)