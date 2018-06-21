package com.orogersilva.spotmusicalarm.dashboarddomain.dto

import com.google.gson.annotations.SerializedName

data class PlaylistPagingDTO(@SerializedName("items") val items: List<PlaylistDTO>,
                             @SerializedName("href") val href: String,
                             @SerializedName("limit") val limit: Int,
                             @SerializedName("next") val next: String?,
                             @SerializedName("offset") val offset: Int,
                             @SerializedName("previous") val previous: String?,
                             @SerializedName("total") val total: Int)