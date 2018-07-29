package com.orogersilva.spotmusicalarm.dashboarddata.remote

import com.nhaarman.mockitokotlin2.whenever
import com.orogersilva.spotmusicalarm.dashboarddata.contract.UserDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.di.component.DaggerTestUserRepositoryComponent
import com.orogersilva.spotmusicalarm.dashboarddata.di.module.TestUserRepositoryModule
import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.UserEntity
import com.orogersilva.spotmusicalarm.dashboarddata.remote.endpoint.server.BaseRemoteClientTest
import com.orogersilva.spotmusicalarm.dashboarddomain.SpotifyRegularErrorException
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Test
import javax.inject.Inject

class UserRemoteDataSourceTest : BaseRemoteClientTest() {

    // region PROPERTIES

    @Inject lateinit var userLocalDataSourceMock: UserDataContract.Local

    @Inject lateinit var userRemoteDataSource: UserDataContract.Remote

    // endregion

    // region TEST METHODS

    @Test fun `Get me, when user is not authenticated, then return SpotifyRegularErrorException`() {

        // ARRANGE

        val ACCESS_TOKEN = null

        whenever(userLocalDataSourceMock.getAccessToken()).thenReturn(ACCESS_TOKEN)

        val testObserver = TestObserver<UserEntity>()

        // ACT

        userRemoteDataSource.getMe()
                .subscribe(testObserver)

        // ASSERT

        testObserver
                .assertNotComplete()
                .assertError(SpotifyRegularErrorException::class.java)
                .assertErrorMessage(UNAUTHORIZED_STATUS_MESSAGE)
    }

    @Test fun `Get me, when user is authenticated, then return UserEntity`() {

        // ARRANGE

        val EMITTED_VALUE_COUNT = 1

        val ACCESS_TOKEN = "k3dKssoamxMa20dj3Lzamk1La9ssamxLDfjsKE3d9dmxMxnzSKie20da0sk2"

        val ID = "90453698768"
        val DISPLAY_NAME = "Kevin Watt"

        val expectedUserEntity = UserEntity(ID, DISPLAY_NAME)

        whenever(userLocalDataSourceMock.getAccessToken()).thenReturn(ACCESS_TOKEN)

        val testObserver = TestObserver<UserEntity>()

        // ACT

        userRemoteDataSource.getMe()
                .subscribe(testObserver)

        // ASSERT

        testObserver
                .assertNoErrors()
                .assertComplete()
                .assertValueCount(EMITTED_VALUE_COUNT)
                .assertOf { userEntity ->
                    (userEntity == Single.just(expectedUserEntity))
                }
    }

    // endregion

    // region OVERRIDED METHODS

    override fun injectDependencies() {

        val testComponent = DaggerTestUserRepositoryComponent.builder()
                .testUserRepositoryModule(TestUserRepositoryModule(mockWebServer))
                .build()

        testComponent.inject(this)
    }

    // endregion
}