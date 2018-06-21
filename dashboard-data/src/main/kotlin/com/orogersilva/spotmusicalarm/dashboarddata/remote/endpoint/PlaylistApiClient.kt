package com.orogersilva.spotmusicalarm.dashboarddata.remote.endpoint

import com.orogersilva.spotmusicalarm.dashboarddomain.dto.PlaylistPagingDTO
import com.orogersilva.spotmusicalarm.dashboarddomain.dto.PlaylistDTO
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface PlaylistApiClient {

    // region ENDPOINTS

    @Headers(
            "Content-Type: application/json"
    )
    @GET("users/{user_id}/playlists") fun getUserPlaylists(@Path("user_id") userId: String): Maybe<PlaylistPagingDTO<PlaylistDTO>>

    // endregion
}