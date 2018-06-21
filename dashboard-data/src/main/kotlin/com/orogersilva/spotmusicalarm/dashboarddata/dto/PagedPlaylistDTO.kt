package com.orogersilva.spotmusicalarm.dashboarddata.dto

import com.google.gson.annotations.SerializedName

data class PagedPlaylistDTO(@SerializedName("items") val items: List<PlaylistDTO>,
                            @SerializedName("href") val href: String,
                            @SerializedName("limit") val limit: Int,
                            @SerializedName("next") val next: String?,
                            @SerializedName("offset") val offset: Int,
                            @SerializedName("previous") val previous: String?,
                            @SerializedName("total") val total: Int)