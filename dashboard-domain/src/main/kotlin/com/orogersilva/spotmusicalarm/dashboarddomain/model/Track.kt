package com.orogersilva.spotmusicalarm.dashboarddomain.model

data class Track(val id: String,
                 val name: String,
                 val artists: List<Artist>) {

    // region PUBLIC METHODS

    fun getFormattedArtists(): String =
            artists.joinToString { artist -> artist.name }

    // endregion
}