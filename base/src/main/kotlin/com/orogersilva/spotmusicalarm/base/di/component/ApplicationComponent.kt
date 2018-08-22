package com.orogersilva.spotmusicalarm.base.di.component

import android.content.Context
import android.content.SharedPreferences
import com.orogersilva.spotmusicalarm.base.di.module.ApplicationModule
import com.orogersilva.spotmusicalarm.base.di.module.PreferencesModule
import com.orogersilva.spotmusicalarm.base.di.module.SchedulerProviderModule
import com.orogersilva.spotmusicalarm.base.scheduler.SchedulerProvider
import com.orogersilva.spotmusicalarm.base.wrapper.SharedPreferencesWrapperContract
import com.orogersilva.spotmusicalarm.base.wrapper.impl.SharedPreferencesWrapper
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            ApplicationModule::class,
            PreferencesModule::class,
            SchedulerProviderModule::class
        ]
)
interface ApplicationComponent {

    // region EXPOSED DEPENDENCIES

    fun context(): Context

    fun schedulerProvider(): SchedulerProvider

    fun sharedPreferencesWrapper(): SharedPreferencesWrapperContract

    // endregion
}