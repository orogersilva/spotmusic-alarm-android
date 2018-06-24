package com.orogersilva.spotmusicalarm.dashboarddomain.local

interface LoginPreferencesDataSource {

    // region METHODS

    fun getAccessToken(): String

    fun saveAccessToken(accessToken: String)

    // endregion
}