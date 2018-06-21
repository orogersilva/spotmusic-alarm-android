package com.orogersilva.spotmusicalarm.dashboarddata.remote

import com.orogersilva.spotmusicalarm.dashboarddata.remote.endpoint.PlaylistApiClient
import com.orogersilva.spotmusicalarm.dashboarddomain.PlaylistDataContract
import javax.inject.Inject

class PlaylistRemoteDataSource @Inject constructor(private val playlistApiClient: PlaylistApiClient) : PlaylistDataContract.Remote {

    // region OVERRIDED METHODS

    override fun getUserPlaylists(userId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // endregion
}