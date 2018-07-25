package com.orogersilva.spotmusicalarm.dashboarddata.remote.endpoint

import com.orogersilva.spotmusicalarm.dashboarddata.dto.PagingDTO
import com.orogersilva.spotmusicalarm.dashboarddata.dto.PlaylistDTO
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface PlaylistApiClient {

    // region ENDPOINTS

    @Headers(
            "Accept: application/json",
            "Content-Type: application/json"
    )
    @GET("me/playlists") fun getPagedPlaylists(@Query("limit") limit: Int,
                                               @Query("offset") offset: Int): Single<Response<ResponseBody>>

    // endregion
}