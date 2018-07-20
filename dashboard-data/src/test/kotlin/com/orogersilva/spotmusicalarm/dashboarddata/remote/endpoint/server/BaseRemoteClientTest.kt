package com.orogersilva.spotmusicalarm.dashboarddata.remote.endpoint.server

import com.orogersilva.spotmusicalarm.dashboarddata.di.component.DaggerTestBaseRemoteDataSourceComponent
import com.orogersilva.spotmusicalarm.dashboarddata.di.component.TestBaseRemoteDataSourceComponent
import com.orogersilva.spotmusicalarm.dashboarddata.di.module.TestBaseRemoteDataSourceModule
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import javax.inject.Inject

abstract class BaseRemoteClientTest {

    // region PROPERTIES

    protected lateinit var testBaseRemoteDataSourceComponent: TestBaseRemoteDataSourceComponent

    @Inject lateinit var mockWebServer: MockWebServer

    // endregion

    // region ABSTRACT METHODS

    abstract fun initializeMocks()

    abstract fun injectDependencies(testBaseRemoteDataSourceComponent: TestBaseRemoteDataSourceComponent)

    // endregion

    // region SETUP METHOD

    @Before fun setUp() {

        testBaseRemoteDataSourceComponent = DaggerTestBaseRemoteDataSourceComponent.builder()
                .testBaseRemoteDataSourceModule(TestBaseRemoteDataSourceModule())
                .build()

        initializeMocks()
        injectDependencies(testBaseRemoteDataSourceComponent)
    }

    // endregion

    // region TEARDOWN METHOD

    @After fun teardown() {

        mockWebServer.shutdown()
    }

    // endregion
}