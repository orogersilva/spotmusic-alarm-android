package com.orogersilva.spotmusicalarm.dashboarddomain.model

import com.orogersilva.spotmusicalarm.dashboarddomain.enum.Status
import com.orogersilva.spotmusicalarm.dashboarddomain.enum.Status.*

class Response<T> private constructor(private val status: Status,
                                      private val data: T?,
                                      private val error: Throwable?) {

    // region COMPANION OBJECT

    companion object {

        // region METHODS

        fun <T> loading(): Response<T> =
                Response(LOADING, null, null)

        fun <T> success(data: T): Response<T> =
                Response(SUCCESS, data, null)

        fun <T> error(error: Throwable): Response<T> =
                Response(ERROR, null, error)

        // endregion
    }

    // endregion
}