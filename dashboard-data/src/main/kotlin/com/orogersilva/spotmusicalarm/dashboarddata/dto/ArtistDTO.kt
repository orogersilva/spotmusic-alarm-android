package com.orogersilva.spotmusicalarm.dashboarddata.dto

import com.google.gson.annotations.SerializedName

data class ArtistDTO(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String
)