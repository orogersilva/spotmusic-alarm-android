package com.orogersilva.spotmusicalarm.dashboarddata.remote.endpoint.server

import com.orogersilva.spotmusicalarm.dashboarddata.remote.FORBIDDEN_STATUS_CODE
import com.orogersilva.spotmusicalarm.dashboarddata.remote.NOT_FOUND_STATUS_CODE
import com.orogersilva.spotmusicalarm.dashboarddata.remote.OK_STATUS_CODE
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

            mockResponse.setBody(readFile(SPOTIFY_REGULAR_ERROR_FILE_NAME))
            mockResponse.setResponseCode(FORBIDDEN_STATUS_CODE)

        } else {

            val scenario = getScenario(request)

            scenario?.let {

                mockResponse.setBody(readFile(it))

                when (request.path) {

                    "/me" -> {
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

            val path = it.path
            val requestMethod = it.method.toLowerCase(Locale.US)

            scenario = requestMethod + path.replace("/", "_") + ".json"
        }

        return scenario
    }

    private fun readFile(jsonFileName: String): String? {

        val inputStream = javaClass.classLoader.getResourceAsStream(jsonFileName)

        if (inputStream == null) {

            throw NullPointerException("Have you added the local resource correctly? " +
                    "Hint name it as: " + jsonFileName)
        }

        var jsonStr: String? = null

        try {

            val size = inputStream.available()
            val buffer = ByteArray(size)

            inputStream.read(buffer)

            jsonStr = String(buffer, Charset.forName("UTF-8"))

        } catch (e: IOException) {

            e.printStackTrace()

            return null

        } finally {

            inputStream.close()
        }

        return jsonStr
    }

    // endregion
}