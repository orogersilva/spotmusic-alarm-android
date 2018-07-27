package com.orogersilva.spotmusicalarm.dashboarddata.mapper

import com.orogersilva.spotmusicalarm.dashboarddata.dto.TrackDTO
import com.orogersilva.spotmusicalarm.dashboarddata.entity.ArtistEntity
import com.orogersilva.spotmusicalarm.dashboarddata.entity.TrackEntity
import com.orogersilva.spotmusicalarm.dashboarddata.relation.TrackAndAllArtists

object TrackMapper {

    // region PUBLIC METHODS

    fun transformTrackDTOsToTrackAndAllArtistsListSupportedByPlaylistId(trackDTOs: List<TrackDTO>,
                                                                        playlistId: String): List<TrackAndAllArtists> {

        val trackAndAllArtistsList = mutableListOf<TrackAndAllArtists>()

        trackDTOs.forEach { trackDTO ->

            val artistEntities = mutableListOf<ArtistEntity>()

            artistEntities.addAll(ArtistMapper
                    .transformArtistDTOsToArtistEntitiesSupportedByTrackId(
                            trackDTO.artistDTOs, trackDTO.id
                    )
            )

            val trackAndAllArtists = TrackAndAllArtists()

            trackAndAllArtists.trackEntity = transformTrackDTOToTrackEntitySupportedByPlaylistId(trackDTO, playlistId)
            trackAndAllArtists.artistEntities = artistEntities

            trackAndAllArtistsList.add(trackAndAllArtists)
        }

        return trackAndAllArtistsList
    }

    // endregion

    // region UTILITY METHODS

    private fun transformTrackDTOToTrackEntitySupportedByPlaylistId(trackDTO: TrackDTO,
                                                                    playlistId: String): TrackEntity =
            TrackEntity(trackDTO.id, trackDTO.name, playlistId)

    // endregion
}