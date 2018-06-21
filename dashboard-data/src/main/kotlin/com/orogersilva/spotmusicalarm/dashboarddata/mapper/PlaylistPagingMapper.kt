package com.orogersilva.spotmusicalarm.dashboarddata.mapper

import com.orogersilva.spotmusicalarm.dashboarddata.entity.PagedPlaylistEntity
import com.orogersilva.spotmusicalarm.dashboarddata.dto.PagedPlaylistDTO
import com.orogersilva.spotmusicalarm.dashboarddomain.model.PagedPlaylist

object PlaylistPagingMapper {

    // region METHODS

    fun transformPagedPlaylistDTOToPagedPlaylistEntity(pagedPlaylistDTO: PagedPlaylistDTO): PagedPlaylistEntity {

        val playlistEntities = PlaylistMapper
                .transformPlaylistDTOsToPlaylistEntities(pagedPlaylistDTO.items)

        return PagedPlaylistEntity(playlistEntities, pagedPlaylistDTO.limit, pagedPlaylistDTO.next,
                pagedPlaylistDTO.offset, pagedPlaylistDTO.previous, pagedPlaylistDTO.total)
    }

    fun transformPagedPlaylistEntityToPagedPlaylist(pagedPlaylistEntity: PagedPlaylistEntity): PagedPlaylist {

        val playlists = PlaylistMapper.transformPlaylistEntitiesToPlaylists(pagedPlaylistEntity.items)

        return PagedPlaylist(playlists, pagedPlaylistEntity.limit, pagedPlaylistEntity.next,
                pagedPlaylistEntity.offset, pagedPlaylistEntity.previous, pagedPlaylistEntity.total)
    }

    // endregion
}