package com.orogersilva.spotmusicalarm.base.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class ApplicationModule(private val context: Context) {

    // region PROVIDERS

    @Provides @Singleton open fun provideContext(): Context = context

    // endregion
}