package com.orogersilva.spotmusicalarm.dashboarddata.remote.endpoint

import com.orogersilva.spotmusicalarm.dashboarddata.dto.PagedPlaylistDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface PlaylistApiClient {

    // region ENDPOINTS

    @Headers(
            "Content-Type: application/json"
    )
    @GET("users/{user_id}/playlists") fun getPagedUserPlaylists(@Path("user_id") userId: String): Single<PagedPlaylistDTO>

    // endregion
}