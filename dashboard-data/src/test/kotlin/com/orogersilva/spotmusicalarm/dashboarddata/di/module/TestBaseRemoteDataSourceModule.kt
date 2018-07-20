package com.orogersilva.spotmusicalarm.dashboarddata.di.module

import dagger.Module
import dagger.Provides
import okhttp3.mockwebserver.MockWebServer

@Module
open class TestBaseRemoteDataSourceModule {

    // region PROVIDERS

    @Provides open fun provideMockWebServer(): MockWebServer = MockWebServer()

    // endregion
}