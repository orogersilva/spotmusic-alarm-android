package com.orogersilva.spotmusicalarm.dashboarddata.dto

import com.google.gson.annotations.SerializedName

data class PlaylistDTO(@SerializedName("collaborative") val isCollaborative: Boolean,
                       @SerializedName("href") val href: String,
                       @SerializedName("id") val id: String,
                       @SerializedName("images") val images: List<Image>,
                       @SerializedName("name") val name: String,
                       @SerializedName("owner") val owner: User,
                       @SerializedName("public") val isPublic: Boolean?,
                       @SerializedName("tracks") val tracks: List<Track>,
                       @SerializedName("type") val type: String,
                       @SerializedName("uri") val uri: String) {

    // region INNER CLASSES

    inner class Image(@SerializedName("url") val url: String,
                      @SerializedName("width") val width: Int?,
                      @SerializedName("height") val height: Int?)

    inner class Track(@SerializedName("id") val id: String,
                      @SerializedName("name") val name: String,
                      @SerializedName("preview_url") val previewUrl: String,
                      @SerializedName("type") val type: String,
                      @SerializedName("uri") val uri: String)

    inner class User(@SerializedName("display_name") val displayName: String?,
                     @SerializedName("id") val id: String,
                     @SerializedName("images") val images: List<Image>,
                     @SerializedName("type") val type: String,
                     @SerializedName("uri") val uri: String)

    // endregion
}