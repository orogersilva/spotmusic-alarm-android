package com.orogersilva.spotmusicalarm.dashboarddata.dto

import com.google.gson.annotations.SerializedName

data class PlaylistTrackDTO(@SerializedName("track") val trackDTO: TrackDTO)