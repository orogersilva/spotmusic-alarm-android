package com.orogersilva.spotmusicalarm.dashboarddata.mapper

import com.orogersilva.spotmusicalarm.dashboarddata.dto.TrackDTO
import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.ArtistEntity
import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.TrackEntity
import com.orogersilva.spotmusicalarm.dashboarddata.local.relation.TrackAndAllArtists
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Track

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

    fun transformTrackAndAllArtistsListToTracks(trackAndAllArtistsList: List<TrackAndAllArtists>): List<Track> {

        val tracks = mutableListOf<Track>()

        trackAndAllArtistsList.forEach {
            tracks.add(transformTrackAndAllArtistsToTrack(it))
        }

        return tracks
    }

    // endregion

    // region UTILITY METHODS

    private fun transformTrackDTOToTrackEntitySupportedByPlaylistId(trackDTO: TrackDTO,
                                                                    playlistId: String): TrackEntity =
            TrackEntity(trackDTO.id, trackDTO.name, playlistId)

    private fun transformTrackAndAllArtistsToTrack(trackAndAllArtists: TrackAndAllArtists): Track =
            Track(trackAndAllArtists.trackEntity?.id!!, trackAndAllArtists.trackEntity?.name!!,
                ArtistMapper.transformArtistEntitiesToArtists(trackAndAllArtists.artistEntities))

    // endregion
}