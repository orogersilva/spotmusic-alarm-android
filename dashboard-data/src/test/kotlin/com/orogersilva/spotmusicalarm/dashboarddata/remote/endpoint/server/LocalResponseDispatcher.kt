package com.orogersilva.spotmusicalarm.dashboarddata.remote.endpoint.server

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

        val scenario = getScenario(request)

        if (scenario != null) {

            try {

                mockResponse.setBody(readFile(scenario))
                mockResponse.setResponseCode(OK_STATUS_CODE)

            } catch (e: IOException) {

                e.printStackTrace()
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

        /*val inputStream = LocalResponseDispatcher::class.java
                .getResourceAsStream(jsonFileName)*/

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