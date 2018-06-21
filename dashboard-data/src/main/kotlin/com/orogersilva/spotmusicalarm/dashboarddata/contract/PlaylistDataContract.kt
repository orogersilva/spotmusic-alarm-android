package com.orogersilva.spotmusicalarm.dashboarddata.contract

import com.orogersilva.spotmusicalarm.dashboarddata.entity.PagedPlaylistEntity
import io.reactivex.Single

interface PlaylistDataContract {

    // region INTERFACES

    interface Remote {

        // region METHODS

        fun getPagedUserPlaylists(userId: String): Single<PagedPlaylistEntity>

        // endregion
    }

    // endregion
}