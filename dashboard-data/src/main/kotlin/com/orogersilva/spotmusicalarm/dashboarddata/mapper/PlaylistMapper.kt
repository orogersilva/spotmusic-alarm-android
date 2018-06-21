package com.orogersilva.spotmusicalarm.dashboarddata.mapper

import com.orogersilva.spotmusicalarm.dashboarddata.entity.PlaylistEntity
import com.orogersilva.spotmusicalarm.dashboarddata.dto.PlaylistDTO
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

    // endregion

    // region UTILITY METHODS

    private fun transformPlaylistDTOToPlaylistEntity(playlistDTO: PlaylistDTO): PlaylistEntity =
            PlaylistEntity(playlistDTO.id, playlistDTO.name)

    private fun transformPlaylistEntityToPlaylist(playlistEntity: PlaylistEntity): Playlist =
            Playlist(playlistEntity.id, playlistEntity.name)

    // endregion
}