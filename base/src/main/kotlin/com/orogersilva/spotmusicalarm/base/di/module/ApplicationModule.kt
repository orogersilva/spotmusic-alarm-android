package com.orogersilva.spotmusicalarm.base.di.module

import android.content.Context
import com.orogersilva.spotmusicalarm.base.SpotmusicAlarmApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ApplicationModule {

    // region PROVIDERS

    @Provides @Singleton @JvmStatic fun provideContext(application: SpotmusicAlarmApplication): Context = application



    // endregion
}