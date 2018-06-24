package com.orogersilva.spotmusicalarm.dashboarddata.remote

import com.orogersilva.spotmusicalarm.dashboarddata.remote.endpoint.UserApiClient
import com.orogersilva.spotmusicalarm.dashboarddata.remote.server.BaseApiClientTestCase
import io.reactivex.observers.TestObserver
import okhttp3.ResponseBody
import org.junit.Test
import retrofit2.Response
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals

class UserApiClientTest : BaseApiClientTestCase() {

    // region PROPERTIES

    private lateinit var userApiClient: UserApiClient

    // endregion

    // region TEST METHODS

    @Test fun `Get me, when i am authenticated, then return UserDTO`() {

        // ARRANGE

        val EMITTED_VALUE_COUNT = 1

        val expectedPath = "/me"

        val testObserver = TestObserver<Response<ResponseBody>>()

        // ACT

        userApiClient.getMe()
                .subscribe(testObserver)

        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS)

        // ASSERT

        testObserver
                .assertNoErrors()
                .assertComplete()
                .assertValueCount(EMITTED_VALUE_COUNT)

        val recordedRequest = mockWebServer.takeRequest()

        assertEquals(expectedPath, recordedRequest.path)
    }

    // endregion

    /**/

    // region OVERRIDED METHODS

    override fun initializeApiClient() {

        userApiClient = RestClient.getApiClient(UserApiClient::class.java, getBaseUrl())
    }

    // endregion
}