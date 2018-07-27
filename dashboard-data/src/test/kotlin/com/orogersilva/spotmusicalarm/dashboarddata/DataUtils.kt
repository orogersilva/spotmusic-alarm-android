package com.orogersilva.spotmusicalarm.dashboarddata

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.orogersilva.spotmusicalarm.dashboarddata.dto.PagingDTO
import com.orogersilva.spotmusicalarm.dashboarddata.dto.PlaylistTrackDTO
import com.orogersilva.spotmusicalarm.dashboarddata.entity.PlaylistEntity
import com.orogersilva.spotmusicalarm.dashboarddata.mapper.PlaylistMapper
import com.orogersilva.spotmusicalarm.dashboarddata.relation.TrackAndAllArtists
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Paging

object DataUtils {

    // region PUBLIC METHODS

    fun createPagedPlaylistEntitiesTestData(jsonStr: String?): Paging<PlaylistEntity> {

        val type = object : TypeToken<Paging<PlaylistEntity>>(){}.type

        val pagedPlaylistEntities = Gson().fromJson<Paging<PlaylistEntity>>(jsonStr, type)

        return pagedPlaylistEntities
    }

    fun createPagedTrackAndAllArtistsTestData(jsonStr: String?): Paging<TrackAndAllArtists> {

        val ANY_PLAYLIST_ID = "0"

        val type = object : TypeToken<PagingDTO<PlaylistTrackDTO>>(){}.type

        val pagedPlaylistTrackDTO = Gson().fromJson<PagingDTO<PlaylistTrackDTO>>(jsonStr, type)

        return PlaylistMapper
                .transformPagedPlaylistTrackDTOsToPagedTrackAndAllArtistsSupportedByPlaylistId(
                        pagedPlaylistTrackDTO,
                        ANY_PLAYLIST_ID)
    }

    // endregion
}