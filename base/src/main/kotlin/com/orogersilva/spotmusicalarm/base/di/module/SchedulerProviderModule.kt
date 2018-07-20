package com.orogersilva.spotmusicalarm.base.di.module

import com.orogersilva.spotmusicalarm.base.scheduler.SchedulerProvider
import com.orogersilva.spotmusicalarm.base.scheduler.impl.AppSchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class SchedulerProviderModule {

    // region PROVIDERS

    @Provides @Singleton open fun provideSchedulerProvider(): SchedulerProvider =
            AppSchedulerProvider()

    // endregion
}