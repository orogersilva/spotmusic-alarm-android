package com.orogersilva.spotmusicalarm.testutils.shared

import java.io.IOException
import java.nio.charset.Charset

object FileUtils {

    // region PUBLIC METHODS

    fun readFile(jsonFileName: String): String? {

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