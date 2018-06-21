package com.orogersilva.spotmusicalarm.dashboarddata.repository

import com.orogersilva.spotmusicalarm.dashboarddata.contract.PlaylistDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.mapper.PlaylistPagingMapper
import com.orogersilva.spotmusicalarm.dashboarddomain.model.PagedPlaylist
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.PlaylistRepository
import io.reactivex.Single
import javax.inject.Inject

class PlaylistDataRepository @Inject constructor(private val playlistRemoteDataSource: PlaylistDataContract.Remote) : PlaylistRepository {

    // region OVERRIDED METHODS

    override fun getPagedUserPlaylists(userId: String): Single<PagedPlaylist> =
            playlistRemoteDataSource.getPagedUserPlaylists(userId)
                .flatMap { pagedPlaylistEntity -> Single.just(PlaylistPagingMapper
                        .transformPagedPlaylistEntityToPagedPlaylist(pagedPlaylistEntity)) }

    // endregion
}