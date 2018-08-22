package com.orogersilva.spotmusicalarm.dashboarddata.local

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.orogersilva.spotmusicalarm.base.wrapper.SharedPreferencesWrapperContract
import com.orogersilva.spotmusicalarm.dashboarddata.contract.UserDataContract
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class UserLocalDataSourceTest {

    // region PROPERTIES

    private lateinit var sharedPreferencesWrapperMock: SharedPreferencesWrapperContract

    private lateinit var userLocalDataSource: UserDataContract.Local

    // endregion

    // region SETUP METHOD

    @Before fun setUp() {

        sharedPreferencesWrapperMock = mock()

        userLocalDataSource = UserLocalDataSource(sharedPreferencesWrapperMock)
    }

    // endregion

    // region TEST METHODS

    @Test fun `Get access token, when access token does not exist, then return null`() {

        // ARRANGE

        val ACCESS_TOKEN_PREF_KEY = "ACCESS_TOKEN_PREF_KEY"

        whenever(sharedPreferencesWrapperMock.getString(ACCESS_TOKEN_PREF_KEY, null))
            .thenReturn(null)

        // ACT

        val nullAccessToken = userLocalDataSource.getAccessToken()

        // ASSERT

        assertNull(nullAccessToken)
    }

    @Test fun `Get access token, when access token exist, then return access token`() {

        // ARRANGE

        val ACCESS_TOKEN_PREF_KEY = "ACCESS_TOKEN_PREF_KEY"
        val EXPECTED_ACCESS_TOKEN = "k3dKssoamxMa20dj3Lzamk1La9ssamxLDfjsKE3d9dmxMxnzSKie20da0sk2"

        whenever(sharedPreferencesWrapperMock.getString(ACCESS_TOKEN_PREF_KEY, null))
            .thenReturn(EXPECTED_ACCESS_TOKEN)

        // ACT

        val accessToken = userLocalDataSource.getAccessToken()

        // ASSERT

        assertEquals(EXPECTED_ACCESS_TOKEN, accessToken)
    }

    @Test fun `Save access token, when access token is provided, then access token is persisted successfully`() {

        // ARRANGE

        val ACCESS_TOKEN_PREF_KEY = "ACCESS_TOKEN_PREF_KEY"
        val ACCESS_TOKEN = "k3dKssoamxMa20dj3Lzamk1La9ssamxLDfjsKE3d9dmxMxnzSKie20da0sk2"

        // ACT

        userLocalDataSource.saveAccessToken(ACCESS_TOKEN)

        // ASSERT

        verify(sharedPreferencesWrapperMock).putString(ACCESS_TOKEN_PREF_KEY, ACCESS_TOKEN)
        verify(sharedPreferencesWrapperMock).commit()
    }

    // endregion
}