package com.orogersilva.spotmusicalarm.base.di.component

import android.content.Context
import android.content.SharedPreferences
import com.orogersilva.spotmusicalarm.base.SpotmusicAlarmApplication
import com.orogersilva.spotmusicalarm.base.di.module.ApplicationModule
import com.orogersilva.spotmusicalarm.base.di.module.PreferencesModule
import com.orogersilva.spotmusicalarm.base.di.module.SchedulerProviderModule
import com.orogersilva.spotmusicalarm.base.scheduler.SchedulerProvider
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
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

    fun sharedPreferences(): SharedPreferences

    fun sharedPreferencesEditor(): SharedPreferences.Editor

    // endregion
}