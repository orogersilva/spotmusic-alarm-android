package com.orogersilva.spotmusicalarm.dashboarddata.remote

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.orogersilva.spotmusicalarm.dashboarddata.contract.PlaylistDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.dto.PagingDTO
import com.orogersilva.spotmusicalarm.dashboarddata.dto.PlaylistDTO
import com.orogersilva.spotmusicalarm.dashboarddata.dto.PlaylistTrackDTO
import com.orogersilva.spotmusicalarm.dashboarddata.dto.SpotifyRegularErrorDTO
import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.PlaylistEntity
import com.orogersilva.spotmusicalarm.dashboarddata.mapper.PlaylistMapper
import com.orogersilva.spotmusicalarm.dashboarddata.local.relation.TrackAndAllArtists
import com.orogersilva.spotmusicalarm.dashboarddata.remote.endpoint.PlaylistApiClient
import com.orogersilva.spotmusicalarm.dashboarddomain.SpotifyRegularErrorException
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Paging
import io.reactivex.Single
import javax.inject.Inject

class PlaylistRemoteDataSource @Inject constructor(private val playlistApiClient: PlaylistApiClient) : PlaylistDataContract.Remote {

    // region OVERRIDED METHODS

    override fun getPagedPlaylists(limit: Int, offset: Int): Single<Paging<PlaylistEntity>> {

        return playlistApiClient.getPagedPlaylists(limit, offset)
                .flatMap { pagedPlaylistDTOsHttpResponse ->

                    when (pagedPlaylistDTOsHttpResponse.code()) {

                        OK_STATUS_CODE -> {

                            val content = pagedPlaylistDTOsHttpResponse.body()?.string()

                            val type = object : TypeToken<PagingDTO<PlaylistDTO>>(){}.type

                            val pagingDTO = Gson().fromJson<PagingDTO<PlaylistDTO>>(content, type)

                            Single.just(PlaylistMapper.transformPagedPlaylistDTOsToPagedPlaylistEntities(pagingDTO))
                        }

                        else -> {

                            val content = pagedPlaylistDTOsHttpResponse.errorBody()?.string()

                            val type = object : TypeToken<SpotifyRegularErrorDTO>(){}.type

                            val spotifyRegularErrorDTO = Gson().fromJson<SpotifyRegularErrorDTO>(content, type)

                            Single.error(
                                    SpotifyRegularErrorException(
                                            spotifyRegularErrorDTO.error.status,
                                            spotifyRegularErrorDTO.error.message
                                    )
                            )
                        }
                    }
                }
    }

    override fun getPagedPlaylistsByUserId(userId: String, limit: Int, offset: Int): Single<Paging<PlaylistEntity>> {

        return playlistApiClient.getPagedPlaylistsByUserId(userId, limit, offset)
                .flatMap { pagedPlaylistDTOsHttpResponse ->

                    when (pagedPlaylistDTOsHttpResponse.code()) {

                        OK_STATUS_CODE -> {

                            val content = pagedPlaylistDTOsHttpResponse.body()?.string()

                            val type = object : TypeToken<PagingDTO<PlaylistDTO>>(){}.type

                            val pagingDTO = Gson().fromJson<PagingDTO<PlaylistDTO>>(content, type)

                            Single.just(PlaylistMapper.transformPagedPlaylistDTOsToPagedPlaylistEntities(pagingDTO))
                        }

                        else -> {

                            val content = pagedPlaylistDTOsHttpResponse.errorBody()?.string()

                            val type = object : TypeToken<SpotifyRegularErrorDTO>(){}.type

                            val spotifyRegularErrorDTO = Gson().fromJson<SpotifyRegularErrorDTO>(content, type)

                            Single.error(
                                    SpotifyRegularErrorException(
                                            spotifyRegularErrorDTO.error.status,
                                            spotifyRegularErrorDTO.error.message
                                    )
                            )
                        }
                    }
                }
    }

    override fun getPagedTrackAndAllArtistsFromPlaylist(userId: String,
                                                        playlistId: String,
                                                        limit: Int,
                                                        offset: Int): Single<Paging<TrackAndAllArtists>> {

        return playlistApiClient.getPagedTracksFromPlaylist(userId, playlistId, limit, offset)
                .flatMap { pagedPlaylistTracksDTOsHttpResponse ->

                    when (pagedPlaylistTracksDTOsHttpResponse.code()) {

                        OK_STATUS_CODE -> {

                            val content = pagedPlaylistTracksDTOsHttpResponse.body()?.string()

                            val type = object : TypeToken<PagingDTO<PlaylistTrackDTO>>(){}.type

                            val pagingDTO = Gson().fromJson<PagingDTO<PlaylistTrackDTO>>(content, type)

                            Single.just(PlaylistMapper
                                    .transformPagedPlaylistTrackDTOsToPagedTrackAndAllArtists(
                                            pagingDTO)
                            )
                        }

                        else -> {

                            val content = pagedPlaylistTracksDTOsHttpResponse.errorBody()?.string()

                            val type = object : TypeToken<SpotifyRegularErrorDTO>(){}.type

                            val spotifyRegularErrorDTO = Gson().fromJson<SpotifyRegularErrorDTO>(content, type)

                            Single.error(
                                    SpotifyRegularErrorException(
                                            spotifyRegularErrorDTO.error.status,
                                            spotifyRegularErrorDTO.error.message
                                    )
                            )
                        }
                    }
                }
    }

    // endregion
}