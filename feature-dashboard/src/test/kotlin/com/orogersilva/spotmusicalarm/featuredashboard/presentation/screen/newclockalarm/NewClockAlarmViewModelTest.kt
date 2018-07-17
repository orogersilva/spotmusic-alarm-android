package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.newclockalarm

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.orogersilva.spotmusicalarm.base.scheduler.SchedulerProvider
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.UserRepository
import com.orogersilva.spotmusicalarm.testutils.scheduler.impl.TestSchedulerProvider
import org.junit.Before
import org.junit.Test

class NewClockAlarmViewModelTest {

    // region PROPERTIES

    private lateinit var userRepositoryMock: UserRepository
    private lateinit var schedulerProvider: SchedulerProvider

    private lateinit var newClockAlarmViewModel: NewClockAlarmViewModel

    // endregion

    // region SETUP METHOD

    @Before fun setUp() {

        userRepositoryMock = mock()

        schedulerProvider = TestSchedulerProvider()

        newClockAlarmViewModel = NewClockAlarmViewModel(userRepositoryMock, schedulerProvider)
    }

    // endregion

    // region TEST METHODS

    @Test fun `Save access token, when access token is provided, then verify repository layer is called`() {

        // ARRANGE

        val ACCESS_TOKEN = "k3dKssoamxMa20dj3Lzamk1La9ssamxLDfjsKE3d9dmxMxnzSKie20da0sk2"

        // ACT

        newClockAlarmViewModel.saveAccessToken(ACCESS_TOKEN)

        // ASSERT

        verify(userRepositoryMock).saveAccessToken(ACCESS_TOKEN)
    }

    // endregion
}