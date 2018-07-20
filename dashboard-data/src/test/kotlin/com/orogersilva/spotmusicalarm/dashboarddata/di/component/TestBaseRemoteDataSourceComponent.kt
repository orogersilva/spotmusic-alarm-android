package com.orogersilva.spotmusicalarm.dashboarddata.di.component

import com.orogersilva.spotmusicalarm.dashboarddata.di.module.TestBaseRemoteDataSourceModule
import dagger.Component
import okhttp3.mockwebserver.MockWebServer

@Component(
        modules = [
            TestBaseRemoteDataSourceModule::class
        ]
)
interface TestBaseRemoteDataSourceComponent {

    // region EXPOSED DEPENDENCIES

    fun mockWebServer(): MockWebServer

    // endregion
}