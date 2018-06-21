package com.orogersilva.spotmusicalarm.dashboarddata.mapper

import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.PlaylistPagingEntity
import com.orogersilva.spotmusicalarm.dashboarddomain.dto.PlaylistPagingDTO

object PlaylistPagingMapper {

    // region METHODS

    fun transformPlaylistPagingDTOToPlaylistPagingEntity(playlistPagingDTO: PlaylistPagingDTO): PlaylistPagingEntity {

        val playlistEntities = PlaylistMapper
                .transformPlaylistDTOsToPlaylistEntities(playlistPagingDTO.items)

        return PlaylistPagingEntity(playlistEntities, playlistPagingDTO.limit, playlistPagingDTO.next,
                playlistPagingDTO.offset, playlistPagingDTO.previous, playlistPagingDTO.total)
    }

    // endregion
}