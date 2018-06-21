package com.orogersilva.spotmusicalarm.dashboarddomain

interface PlaylistDataContract {

    // region INTERFACES

    interface Remote {

        // region METHODS

        fun getUserPlaylists(userId: String)

        // endregion
    }

    // endregion
}