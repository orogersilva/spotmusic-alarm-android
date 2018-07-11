package com.orogersilva.spotmusicalarm.dashboarddomain.model

data class Alarm(val id: Long,
                 val trackName: String,
                 val artistName: String,
                 val isEnabled: Boolean)