package com.orogersilva.spotmusicalarm.dashboarddata.di.component

import com.orogersilva.spotmusicalarm.dashboarddata.di.module.TestPlaylistDataSourceModule
import com.orogersilva.spotmusicalarm.dashboarddata.remote.PlaylistRemoteDataSourceTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            TestPlaylistDataSourceModule::class
        ]
)
interface TestPlaylistDataSourceComponent {

    // region INJECTORS

    fun inject(playlistRemoteDataSourceTest: PlaylistRemoteDataSourceTest)

    // endregion
}