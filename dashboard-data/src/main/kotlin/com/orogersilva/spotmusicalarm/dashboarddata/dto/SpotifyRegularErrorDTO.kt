package com.orogersilva.spotmusicalarm.dashboarddata.dto

import com.google.gson.annotations.SerializedName

class SpotifyRegularErrorDTO(@SerializedName("error") val error: Error) {

    // region INNER CLASSES

    inner class Error(@SerializedName("status") val status: Int,
                      @SerializedName("message") val message: String)

    // endregion
}