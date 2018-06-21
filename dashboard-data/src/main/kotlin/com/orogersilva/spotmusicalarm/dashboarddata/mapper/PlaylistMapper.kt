package com.orogersilva.spotmusicalarm.dashboarddata.mapper

import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.PlaylistEntity
import com.orogersilva.spotmusicalarm.dashboarddomain.dto.PlaylistDTO

object PlaylistMapper {

    // region PUBLIC METHODS

    fun transformPlaylistDTOsToPlaylistEntities(playListDTOs: List<PlaylistDTO>): List<PlaylistEntity> {

        val playlistEntities = mutableListOf<PlaylistEntity>()

        playListDTOs.forEach {
            playlistEntities.add(transformPlaylistDTOToPlaylistEntity(it))
        }

        return playlistEntities
    }

    // endregion

    // region UTILITY METHODS

    private fun transformPlaylistDTOToPlaylistEntity(playlistDTO: PlaylistDTO): PlaylistEntity =
            PlaylistEntity(playlistDTO.id, playlistDTO.name)

    // endregion
}