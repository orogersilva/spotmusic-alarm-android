package com.orogersilva.spotmusicalarm.dashboarddata.di.component

import com.orogersilva.spotmusicalarm.base.di.component.ApplicationComponent
import com.orogersilva.spotmusicalarm.base.di.module.PreferencesModule
import com.orogersilva.spotmusicalarm.dashboarddata.di.module.TestUserRepositoryModule
import com.orogersilva.spotmusicalarm.dashboarddata.di.module.UserRepositoryModule
import com.orogersilva.spotmusicalarm.dashboarddata.remote.UserRemoteDataSourceTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            TestUserRepositoryModule::class
        ]
)
interface TestUserRepositoryComponent {

    // region INJECTORS

    fun inject(userRemoteDataSourceTest: UserRemoteDataSourceTest)

    // endregion
}