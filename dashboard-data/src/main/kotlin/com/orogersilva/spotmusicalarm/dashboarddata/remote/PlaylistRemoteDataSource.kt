package com.orogersilva.spotmusicalarm.dashboarddata.remote

import com.orogersilva.spotmusicalarm.dashboarddata.contract.PlaylistDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.remote.endpoint.PlaylistApiClient
import com.orogersilva.spotmusicalarm.dashboarddata.entity.PagedPlaylistEntity
import com.orogersilva.spotmusicalarm.dashboarddata.mapper.PlaylistPagingMapper
import io.reactivex.Single
import javax.inject.Inject

class PlaylistRemoteDataSource @Inject constructor(private val playlistApiClient: PlaylistApiClient) : PlaylistDataContract.Remote {

    // region OVERRIDED METHODS

    override fun getPagedUserPlaylists(userId: String): Single<PagedPlaylistEntity> =
            playlistApiClient.getPagedUserPlaylists(userId)
                .flatMap { pagedPlaylistDTO -> Single.just(PlaylistPagingMapper
                        .transformPagedPlaylistDTOToPagedPlaylistEntity(pagedPlaylistDTO)) }

    // endregion
}