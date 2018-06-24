package com.orogersilva.spotmusicalarm.dashboarddata.remote.endpoint

import com.orogersilva.spotmusicalarm.dashboarddata.dto.UserDTO
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface UserProfileApiClient {

    // region ENDPOINTS

    @GET("me") fun getMe(): Single<Response<ResponseBody>>

    // endregion
}