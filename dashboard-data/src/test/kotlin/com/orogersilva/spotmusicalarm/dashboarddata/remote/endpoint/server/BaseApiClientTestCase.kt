package com.orogersilva.spotmusicalarm.dashboarddata.remote.endpoint.server

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

abstract class BaseApiClientTestCase {

    // region PROPERTIES

    protected lateinit var mockWebServer: MockWebServer

    // endregion

    // region ABSTRACT METHODS

    abstract fun initializeApiClient()

    // endregion

    // region SETUP METHOD

    @Before fun setUp() {

        mockWebServer = MockWebServer()

        mockWebServer.start()
        mockWebServer.setDispatcher(LocalResponseDispatcher())

        initializeApiClient()
    }

    // endregion

    // region PROTECTED METHODS

    protected fun getBaseUrl(): String = mockWebServer.url("/").toString()

    // endregion

    // region TEARDOWN METHOD

    @After fun teardown() {

        mockWebServer.shutdown()
    }

    // endregion
}