package com.orogersilva.spotmusicalarm.base.di.module

import com.orogersilva.spotmusicalarm.base.di.scope.ActivityScope
import com.orogersilva.spotmusicalarm.base.scheduler.SchedulerProvider
import com.orogersilva.spotmusicalarm.base.scheduler.impl.AppSchedulerProvider
import dagger.Module
import dagger.Provides

@Module
object SchedulerProviderModule {

    // region PROVIDERS

    @Provides @ActivityScope @JvmStatic fun provideSchedulerProvider(): SchedulerProvider =
            AppSchedulerProvider()

    // endregion
}