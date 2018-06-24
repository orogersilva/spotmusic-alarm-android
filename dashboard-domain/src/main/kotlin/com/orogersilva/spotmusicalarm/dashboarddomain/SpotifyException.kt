package com.orogersilva.spotmusicalarm.dashboarddomain

class SpotifyException(val statusCode: Int,
                       val statusMessage: String) : Exception(statusMessage)