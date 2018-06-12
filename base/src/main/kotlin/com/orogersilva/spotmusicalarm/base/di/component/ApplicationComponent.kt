package com.orogersilva.spotmusicalarm.base.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class
        ]
)
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    // region BUILDERS

    @Component.Builder interface Builder {

        @BindsInstance fun application(application: DaggerApplication): Builder

        fun build(): ApplicationComponent
    }

    // endregion
}