package com.orogersilva.spotmusicalarm.dashboarddomain.repository

import com.orogersilva.spotmusicalarm.dashboarddomain.model.PagedPlaylist
import io.reactivex.Single

interface PlaylistRepository {

    // region METHODS

    fun getPagedUserPlaylists(userId: String): Single<PagedPlaylist>

    // endregion
}