package com.orogersilva.spotmusicalarm.base

import com.orogersilva.spotmusicalarm.base.di.component.ApplicationComponent
import com.orogersilva.spotmusicalarm.base.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins

class SpotmusicAlarmApplication : DaggerApplication() {

    // region FIELDS

    val applicationComponent: ApplicationComponent =
            DaggerApplicationComponent.builder().application(this).build()

    // endregion

    // region APPLICATION LIFECYCLE METHODS

    override fun onCreate() {

        super.onCreate()

        // Allowing to emit errors via stream...
        RxJavaPlugins.setErrorHandler {  }
    }

    // endregion

    // region OVERRIDED METHODS

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            applicationComponent.apply {
                inject(this@SpotmusicAlarmApplication)
            }

    // endregion
}