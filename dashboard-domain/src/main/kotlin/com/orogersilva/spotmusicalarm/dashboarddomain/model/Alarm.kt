package com.orogersilva.spotmusicalarm.dashboarddomain.model

data class Alarm(private val id: Long,
                 val trackName: String,
                 val artistName: String,
                 var isEnabled: Boolean)