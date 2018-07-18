package com.orogersilva.spotmusicalarm.dashboarddomain

class SpotifyRegularErrorException(val statusCode: Int,
                                   val statusMessage: String) : Exception(statusMessage)