package com.orogersilva.spotmusicalarm.base

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.orogersilva.spotmusicalarm.base.di.component.ApplicationComponent
import com.orogersilva.spotmusicalarm.base.di.component.DaggerApplicationComponent
import com.orogersilva.spotmusicalarm.base.di.module.ApplicationModule
import com.orogersilva.spotmusicalarm.base.di.module.PreferencesModule
import com.orogersilva.spotmusicalarm.base.di.module.SchedulerProviderModule
import io.fabric.sdk.android.Fabric
import io.reactivex.plugins.RxJavaPlugins

class SpotmusicAlarmApplication : Application() {

    // region FIELDS

    lateinit var applicationComponent: ApplicationComponent

    // endregion

    // region APPLICATION LIFECYCLE METHODS

    override fun onCreate() {

        super.onCreate()

        // Allowing to emit errors via stream...
        RxJavaPlugins.setErrorHandler {  }

        // Initializing Crashlytics...
        Fabric.with(this, Crashlytics())

        applicationComponent = createApplicationComponent()
    }

    // endregion

    // region PUBLIC METHODS

    fun createApplicationComponent(): ApplicationComponent =
            DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .preferencesModule(PreferencesModule())
                .schedulerProviderModule(SchedulerProviderModule())
                .build()

    // endregion
}