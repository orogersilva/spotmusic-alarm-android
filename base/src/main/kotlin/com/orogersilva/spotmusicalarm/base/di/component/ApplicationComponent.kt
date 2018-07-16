package com.orogersilva.spotmusicalarm.base.di.component

import android.content.Context
import com.orogersilva.spotmusicalarm.base.SpotmusicAlarmApplication
import com.orogersilva.spotmusicalarm.base.di.module.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            ApplicationModule::class
        ]
)
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    // region BUILDERS

    @Component.Builder interface Builder {

        @BindsInstance fun application(application: SpotmusicAlarmApplication): Builder

        fun build(): ApplicationComponent
    }

    fun context(): Context

    // endregion
}