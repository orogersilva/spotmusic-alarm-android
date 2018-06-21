package com.orogersilva.spotmusicalarm.dashboarddomain.model

sealed class Response<T> {

    // region COMPANION OBJECT

    companion object {

        // region METHODS

        fun <T> loading(isLoading: Boolean): Response<T> =
                Progress(isLoading)

        fun <T> success(data: T): Response<T> =
                Success(data)

        fun <T> error(error: Throwable): Response<T> =
                Failure(error)

        // endregion
    }

    // endregion

    // region DATA CLASSES

    data class Progress<T>(val isLoading: Boolean): Response<T>()
    data class Success<T>(val data: T): Response<T>()
    data class Failure<T>(val error: Throwable): Response<T>()

    // endregion
}