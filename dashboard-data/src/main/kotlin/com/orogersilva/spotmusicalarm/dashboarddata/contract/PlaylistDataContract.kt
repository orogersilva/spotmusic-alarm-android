package com.orogersilva.spotmusicalarm.dashboarddata.contract

import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.PlaylistEntity
import com.orogersilva.spotmusicalarm.dashboarddata.local.relation.TrackAndAllArtists
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Paging
import io.reactivex.Single

interface PlaylistDataContract {

    // region INTERFACES

    interface Remote {

        // region METHODS

        fun getPagedPlaylists(limit: Int, offset: Int): Single<Paging<PlaylistEntity>>

        fun getPagedPlaylistsByUserId(
            userId: String,
            limit: Int,
            offset: Int
        ): Single<Paging<PlaylistEntity>>

        fun getPagedTrackAndAllArtistsFromPlaylist(
            userId: String,
            playlistId: String,
            limit: Int,
            offset: Int
        ): Single<Paging<TrackAndAllArtists>>

        // endregion
    }

    // endregion
}