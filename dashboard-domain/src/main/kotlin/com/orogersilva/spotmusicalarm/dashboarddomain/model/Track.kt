package com.orogersilva.spotmusicalarm.dashboarddomain.model

import java.io.Serializable

data class Track(val id: String,
                 val name: String,
                 val artists: List<Artist>) : Serializable {

    // region PUBLIC METHODS

    fun getFormattedArtists(): String =
            artists.joinToString { artist -> artist.name }

    // endregion
}