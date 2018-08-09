package com.orogersilva.spotmusicalarm.dashboarddata.dto

import com.google.gson.annotations.SerializedName

data class TrackDTO(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("artists") val artistDTOs: List<ArtistDTO>,
        @SerializedName("preview_url") val previewUrl: String?,
        @SerializedName("uri") val uri: String
)