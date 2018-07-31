package com.orogersilva.spotmusicalarm.dashboarddata.mapper

import com.orogersilva.spotmusicalarm.dashboarddata.dto.ArtistDTO
import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.ArtistEntity
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Artist

object ArtistMapper {

    // region PUBLIC METHODS

    fun transformArtistDTOsToArtistEntitiesSupportedByTrackId(artistDTOs: List<ArtistDTO>, trackId: String): List<ArtistEntity> {

        val artistEntities = mutableListOf<ArtistEntity>()

        artistDTOs.forEach {
            artistEntities.add(transformArtistDTOToArtistEntitySupportedByTrackId(it, trackId))
        }

        return artistEntities
    }

    fun transformArtistEntitiesToArtists(artistEntities: List<ArtistEntity>): List<Artist> {

        val artists = mutableListOf<Artist>()

        artistEntities.forEach {
            artists.add(transformArtistEntityToArtist(it))
        }

        return artists
    }

    fun transformArtistsToArtistEntities(artists: List<Artist>): List<ArtistEntity> {

        val artistEntities = mutableListOf<ArtistEntity>()

        artists.forEach {
            artistEntities.add(transformArtistToArtistEntity(it))
        }

        return artistEntities
    }

    // region UTILITY METHODS

    private fun transformArtistDTOToArtistEntitySupportedByTrackId(artistDTO: ArtistDTO, trackId: String): ArtistEntity =
            ArtistEntity(artistDTO.id, artistDTO.name, trackId)

    private fun transformArtistEntityToArtist(artistEntity: ArtistEntity): Artist =
            Artist(artistEntity.id, artistEntity.name, artistEntity.trackId)

    private fun transformArtistToArtistEntity(artist: Artist): ArtistEntity =
            ArtistEntity(artist.id, artist.name, artist.trackId)

    // endregion

    // endregion
}