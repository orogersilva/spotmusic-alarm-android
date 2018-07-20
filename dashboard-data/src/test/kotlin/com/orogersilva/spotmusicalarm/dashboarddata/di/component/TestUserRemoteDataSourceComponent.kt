package com.orogersilva.spotmusicalarm.dashboarddata.di.component

import com.orogersilva.spotmusicalarm.dashboarddata.di.module.TestBaseRemoteDataSourceModule
import com.orogersilva.spotmusicalarm.dashboarddata.di.module.TestUserRemoteDataSourceModule
import com.orogersilva.spotmusicalarm.dashboarddata.remote.UserRemoteDataSourceTest
import dagger.Component

@Component(
        modules = [
            TestUserRemoteDataSourceModule::class
        ],
        dependencies = [TestBaseRemoteDataSourceComponent::class]
)
interface TestUserRemoteDataSourceComponent {

    // region INJECTORS

    fun inject(userRemoteDataSourceTest: UserRemoteDataSourceTest)

    // endregion
}