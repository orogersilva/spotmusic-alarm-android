package com.orogersilva.spotmusicalarm.dashboarddata.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {

    // region PROPERTIES

    private lateinit var retrofit: Retrofit

    // endregion

    // region PUBLIC METHODS

    fun <T> getApiClient(serviceClass: Class<T>, baseEndpoint: String): T {

        val client = OkHttpClient.Builder()
                .build()

        retrofit = Retrofit.Builder()
                .baseUrl(baseEndpoint)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(serviceClass)
    }

    // endregion
}