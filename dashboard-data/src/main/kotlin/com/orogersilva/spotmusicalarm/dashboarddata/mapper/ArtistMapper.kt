package com.orogersilva.spotmusicalarm.dashboarddata.mapper

import com.orogersilva.spotmusicalarm.dashboarddata.dto.ArtistDTO
import com.orogersilva.spotmusicalarm.dashboarddata.entity.ArtistEntity

object ArtistMapper {

    // region PUBLIC METHODS

    fun transformArtistDTOsToArtistEntitiesSupportedByTrackId(artistDTOs: List<ArtistDTO>, trackId: String): List<ArtistEntity> {

        val artistEntities = mutableListOf<ArtistEntity>()

        artistDTOs.forEach {
            artistEntities.add(transformArtistDTOToArtistEntitySupportedByTrackId(it, trackId))
        }

        return artistEntities
    }

    // region UTILITY METHODS

    private fun transformArtistDTOToArtistEntitySupportedByTrackId(artistDTO: ArtistDTO, trackId: String): ArtistEntity =
            ArtistEntity(artistDTO.id, artistDTO.name, trackId)

    // endregion

    // endregion
}