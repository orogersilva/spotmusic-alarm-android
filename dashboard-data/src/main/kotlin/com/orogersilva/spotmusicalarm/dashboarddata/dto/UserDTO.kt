package com.orogersilva.spotmusicalarm.dashboarddata.dto

import com.google.gson.annotations.SerializedName

data class UserDTO(@SerializedName("display_name") val displayName: String?,
                   @SerializedName("href") val href: String,
                   @SerializedName("id") val id: String,
                   @SerializedName("type") val type: String,
                   @SerializedName("uri") val uri: String)