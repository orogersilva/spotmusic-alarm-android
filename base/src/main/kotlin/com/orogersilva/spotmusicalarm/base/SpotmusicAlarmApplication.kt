package com.orogersilva.spotmusicalarm.base

import android.app.Application
import com.orogersilva.spotmusicalarm.base.di.component.ApplicationComponent
import com.orogersilva.spotmusicalarm.base.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class SpotmusicAlarmApplication : DaggerApplication() {

    // region FIELDS

    val applicationComponent: ApplicationComponent =
            DaggerApplicationComponent.builder().application(this).build()

    // endregion

    // region OVERRIDED METHODS

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            applicationComponent.apply {
                inject(this@SpotmusicAlarmApplication)
            }

    // endregion
}