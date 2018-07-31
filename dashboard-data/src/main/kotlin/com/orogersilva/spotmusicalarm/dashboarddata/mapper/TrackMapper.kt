package com.orogersilva.spotmusicalarm.dashboarddata.mapper

import com.orogersilva.spotmusicalarm.dashboarddata.dto.TrackDTO
import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.ArtistEntity
import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.TrackEntity
import com.orogersilva.spotmusicalarm.dashboarddata.local.relation.TrackAndAllArtists
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Track

object TrackMapper {

    // region PUBLIC METHODS

    fun transformTrackDTOsToTrackAndAllArtistsList(trackDTOs: List<TrackDTO>): List<TrackAndAllArtists> {

        val trackAndAllArtistsList = mutableListOf<TrackAndAllArtists>()

        trackDTOs.forEach { trackDTO ->

            val artistEntities = mutableListOf<ArtistEntity>()

            artistEntities.addAll(ArtistMapper
                    .transformArtistDTOsToArtistEntitiesSupportedByTrackId(
                            trackDTO.artistDTOs, trackDTO.id
                    )
            )

            val trackAndAllArtists = TrackAndAllArtists()

            trackAndAllArtists.trackEntity = transformTrackDTOToTrackEntity(trackDTO)
            trackAndAllArtists.artistEntities = artistEntities

            trackAndAllArtistsList.add(trackAndAllArtists)
        }

        return trackAndAllArtistsList
    }

    fun transformTrackAndAllArtistsListToTracks(trackAndAllArtistsList: List<TrackAndAllArtists>): List<Track> {

        val tracks = mutableListOf<Track>()

        trackAndAllArtistsList.forEach {
            tracks.add(transformTrackAndAllArtistsToTrack(it))
        }

        return tracks
    }

    // endregion

    // region UTILITY METHODS

    private fun transformTrackDTOToTrackEntity(trackDTO: TrackDTO): TrackEntity =
            TrackEntity(trackDTO.id, trackDTO.name)

    private fun transformTrackAndAllArtistsToTrack(trackAndAllArtists: TrackAndAllArtists): Track =
            Track(trackAndAllArtists.trackEntity?.id!!, trackAndAllArtists.trackEntity?.name!!,
                ArtistMapper.transformArtistEntitiesToArtists(trackAndAllArtists.artistEntities))

    // endregion
}