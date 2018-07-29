package com.orogersilva.spotmusicalarm.dashboarddata.mapper

import com.orogersilva.spotmusicalarm.dashboarddata.dto.PagingDTO
import com.orogersilva.spotmusicalarm.dashboarddata.dto.PlaylistDTO
import com.orogersilva.spotmusicalarm.dashboarddata.dto.PlaylistTrackDTO
import com.orogersilva.spotmusicalarm.dashboarddata.dto.TrackDTO
import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.PlaylistEntity
import com.orogersilva.spotmusicalarm.dashboarddata.local.relation.TrackAndAllArtists
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Paging
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Playlist

object PlaylistMapper {

    // region PUBLIC METHODS

    fun transformPlaylistDTOsToPlaylistEntities(playListDTOs: List<PlaylistDTO>): List<PlaylistEntity> {

        val playlistEntities = mutableListOf<PlaylistEntity>()

        playListDTOs.forEach {
            playlistEntities.add(transformPlaylistDTOToPlaylistEntity(it))
        }

        return playlistEntities
    }

    fun transformPlaylistEntitiesToPlaylists(playlistEntities: List<PlaylistEntity>): List<Playlist> {

        val playlists = mutableListOf<Playlist>()

        playlistEntities.forEach {
            playlists.add(transformPlaylistEntityToPlaylist(it))
        }

        return playlists
    }

    fun transformPagedPlaylistDTOsToPagedPlaylistEntities(pagedPlaylistDTOs: PagingDTO<PlaylistDTO>): Paging<PlaylistEntity> =
            Paging(transformPlaylistDTOsToPlaylistEntities(pagedPlaylistDTOs.items),
                pagedPlaylistDTOs.limit,
                pagedPlaylistDTOs.next,
                pagedPlaylistDTOs.offset,
                pagedPlaylistDTOs.previous,
                pagedPlaylistDTOs.total)

    fun transformPagedPlaylistTrackDTOsToPagedTrackAndAllArtistsSupportedByPlaylistId(pagedPlaylistTrackDTOs: PagingDTO<PlaylistTrackDTO>,
                                                                                      playlistId: String): Paging<TrackAndAllArtists> =
            Paging(TrackMapper.transformTrackDTOsToTrackAndAllArtistsListSupportedByPlaylistId(
                transformPlaylistTrackDTOsToTrackDTOs(pagedPlaylistTrackDTOs.items), playlistId),
                pagedPlaylistTrackDTOs.limit,
                pagedPlaylistTrackDTOs.next,
                pagedPlaylistTrackDTOs.offset,
                pagedPlaylistTrackDTOs.previous,
                pagedPlaylistTrackDTOs.total)

    // endregion

    // region UTILITY METHODS

    private fun transformPlaylistDTOToPlaylistEntity(playlistDTO: PlaylistDTO): PlaylistEntity =
            PlaylistEntity(playlistDTO.id, playlistDTO.name)

    private fun transformPlaylistEntityToPlaylist(playlistEntity: PlaylistEntity): Playlist =
            Playlist(playlistEntity.id, playlistEntity.name)

    private fun transformPlaylistTrackDTOsToTrackDTOs(playlistTrackDTOs: List<PlaylistTrackDTO>): List<TrackDTO> {

        val trackDTOs = mutableListOf<TrackDTO>()

        playlistTrackDTOs.forEach {
            trackDTOs.add(it.trackDTO)
        }

        return trackDTOs
    }

    // endregion
}