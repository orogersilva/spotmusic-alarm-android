package com.orogersilva.spotmusicalarm.dashboarddata.remote.endpoint.server

import com.orogersilva.spotmusicalarm.dashboarddata.remote.FORBIDDEN_STATUS_CODE
import com.orogersilva.spotmusicalarm.dashboarddata.remote.NOT_FOUND_STATUS_CODE
import com.orogersilva.spotmusicalarm.dashboarddata.remote.OK_STATUS_CODE
import com.orogersilva.spotmusicalarm.testutils.shared.FileUtils
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.QueueDispatcher
import okhttp3.mockwebserver.RecordedRequest
import java.io.IOException
import java.nio.charset.Charset
import java.util.*

class LocalResponseDispatcher : QueueDispatcher() {

    // region OVERRIDED METHODS

    override fun dispatch(request: RecordedRequest?): MockResponse {

        val mockResponse = MockResponse()

        mockResponse.setResponseCode(NOT_FOUND_STATUS_CODE)

        val accessToken = request?.getHeader("Authorization")

        if (accessToken == null) {

            val SPOTIFY_REGULAR_ERROR_FILE_NAME = "spotify_regular_error.json"

            mockResponse.setBody(FileUtils.readFile(SPOTIFY_REGULAR_ERROR_FILE_NAME))
            mockResponse.setResponseCode(FORBIDDEN_STATUS_CODE)

        } else {

            val scenario = getScenario(request)

            scenario?.let {

                mockResponse.setBody(FileUtils.readFile(it))

                val path = "/" + request.requestUrl.pathSegments().joinTo(buffer = StringBuilder(), separator = "/")

                when (path) {

                    "/me" -> {
                        mockResponse.setResponseCode(OK_STATUS_CODE)
                    }

                    "/me/playlists" -> {

                        mockResponse.setResponseCode(OK_STATUS_CODE)
                    }

                    else -> {
                        mockResponse.setResponseCode(NOT_FOUND_STATUS_CODE)
                    }
                }
            }
        }

        return mockResponse
    }

    // endregion

    // region UTILITY METHODS

    private fun getScenario(request: RecordedRequest?): String? {

        var scenario: String? = null

        request?.let {

            val requestMethod = it.method.toLowerCase(Locale.US)

            val pathSegments = it.requestUrl.pathSegments()

            var path = ""

            pathSegments.forEach { pathSegment ->

                if (path.isEmpty()) {
                    path = pathSegment
                } else {
                    path += "_" + pathSegment
                }
            }

            scenario = requestMethod + "_" + path + ".json"
        }

        return scenario
    }

    // endregion
}