package com.orogersilva.spotmusicalarm.dashboarddata.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.orogersilva.spotmusicalarm.dashboarddata.contract.UserDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.entity.UserEntity
import com.orogersilva.spotmusicalarm.dashboarddata.remote.UNAUTHORIZED_STATUS_CODE
import com.orogersilva.spotmusicalarm.dashboarddata.remote.UNAUTHORIZED_STATUS_MESSAGE
import com.orogersilva.spotmusicalarm.dashboarddomain.SpotifyException
import com.orogersilva.spotmusicalarm.dashboarddomain.model.User
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.UserRepository
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

class UserDataRepositoryTest {

    // region PROPERTIES

    private lateinit var userLocalDataSourceMock: UserDataContract.Local
    private lateinit var userRemoteDataSourceMock: UserDataContract.Remote

    private lateinit var userRepository: UserRepository

    // endregion

    // region SETUP METHOD

    @Before
    fun setUp() {

        userLocalDataSourceMock = mock()
        userRemoteDataSourceMock = mock()

        userRepository = UserDataRepository(userLocalDataSourceMock, userRemoteDataSourceMock)

    }

    // endregion

    // region TEST METHODS

    @Test fun `Get me, when i am not authenticated, then throws SpotifyException`() {

        // ARRANGE

        val expectedSpotifyException =
                SpotifyException(UNAUTHORIZED_STATUS_CODE, UNAUTHORIZED_STATUS_MESSAGE)

        whenever(userRemoteDataSourceMock.getMe()).thenReturn(Single.error(expectedSpotifyException))

        val testObserver = TestObserver<User>()

        // ACT

        userRepository.getMe()
                .subscribe(testObserver)

        // ASSERT

        testObserver
                .assertNotComplete()
                .assertError(expectedSpotifyException)
    }

    @Test fun `Get me, when i am authenticated, then return User`() {

        // ARRANGE

        val ID = "90453698768"
        val DISPLAY_NAME = "Kevin Watt"

        val EMITTED_VALUE_COUNT = 1

        val expectedUserData = Single.just(User(ID))

        val userEntityData = Single.just(UserEntity(ID, DISPLAY_NAME))

        whenever(userRemoteDataSourceMock.getMe()).thenReturn(userEntityData)

        val testObserver = TestObserver<User>()

        // ACT

        userRepository.getMe()
                .subscribe(testObserver)

        // ASSERT

        testObserver
                .assertComplete()
                .assertValueCount(EMITTED_VALUE_COUNT)
                .assertOf { userData -> (userData == expectedUserData) }
    }
}