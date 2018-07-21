package com.orogersilva.spotmusicalarm.dashboarddata.remote.endpoint.server

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import javax.inject.Inject

abstract class BaseRemoteClientTest {

    // region PROPERTIES

    protected lateinit var mockWebServer: MockWebServer

    // endregion

    // region ABSTRACT METHODS

    abstract fun injectDependencies()

    // endregion

    // region SETUP METHOD

    @Before fun setUp() {

        mockWebServer = MockWebServer()

        mockWebServer.start()
        mockWebServer.setDispatcher(LocalResponseDispatcher())

        injectDependencies()
    }

    // endregion

    // region TEARDOWN METHOD

    @After fun teardown() {

        mockWebServer.shutdown()
    }

    // endregion
}