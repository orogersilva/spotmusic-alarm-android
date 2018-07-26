package com.orogersilva.spotmusicalarm.dashboarddata.remote

import com.nhaarman.mockitokotlin2.whenever
import com.orogersilva.spotmusicalarm.dashboarddata.DataUtils
import com.orogersilva.spotmusicalarm.dashboarddata.contract.PlaylistDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.contract.UserDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.di.component.DaggerTestPlaylistDataSourceComponent
import com.orogersilva.spotmusicalarm.dashboarddata.di.module.TestPlaylistDataSourceModule
import com.orogersilva.spotmusicalarm.dashboarddata.entity.PlaylistEntity
import com.orogersilva.spotmusicalarm.dashboarddata.remote.endpoint.server.BaseRemoteClientTest
import com.orogersilva.spotmusicalarm.dashboarddomain.SpotifyRegularErrorException
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Paging
import com.orogersilva.spotmusicalarm.testutils.shared.FileUtils
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Test
import javax.inject.Inject

class PlaylistRemoteDataSourceTest : BaseRemoteClientTest() {

    // region PROPERTIES

    @Inject lateinit var userLocalDataSourceMock: UserDataContract.Local

    @Inject lateinit var playlistRemoteDataSource: PlaylistDataContract.Remote

    // endregion

    // region TEST METHODS

    @Test fun `Get paged playlists, when user is not authenticated, then return SpotifyRegularErrorException`() {

        // ARRANGE

        val ACCESS_TOKEN = null

        val LIMIT = 0
        val OFFSET = 0

        whenever(userLocalDataSourceMock.getAccessToken()).thenReturn(ACCESS_TOKEN)

        val testObserver = TestObserver<Paging<PlaylistEntity>>()

        // ACT

        playlistRemoteDataSource.getPagedPlaylists(LIMIT, OFFSET)
                .subscribe(testObserver)

        // ASSERT

        testObserver.assertNotComplete()
                .assertError(SpotifyRegularErrorException::class.java)
                .assertErrorMessage(UNAUTHORIZED_STATUS_MESSAGE)
    }

    @Test fun `Get paged playlists, when there is page to be fetched, then return page with items`() {

        // ARRANGE

        val DATA_SET_FILE_NAME = "get_me_playlists.json"

        val EMITTED_VALUE_COUNT = 1

        val ACCESS_TOKEN = "k3dKssoamxMa20dj3Lzamk1La9ssamxLDfjsKE3d9dmxMxnzSKie20da0sk2"

        val LIMIT = 10
        val OFFSET = 14

        val expectedPaging = DataUtils.createPagedPlaylistEntitiesTestData(FileUtils.readFile(DATA_SET_FILE_NAME))

        whenever(userLocalDataSourceMock.getAccessToken()).thenReturn(ACCESS_TOKEN)

        val testObserver = TestObserver<Paging<PlaylistEntity>>()

        // ACT

        playlistRemoteDataSource.getPagedPlaylists(LIMIT, OFFSET)
                .subscribe(testObserver)

        // ASSERT

        testObserver
                .assertNoErrors()
                .assertComplete()
                .assertValueCount(EMITTED_VALUE_COUNT)
                .assertOf { paging ->
                    (paging == Single.just(expectedPaging))
                }
    }

    @Test fun `Get paged playlists by user id, when user is not authenticated, then return SpotifyRegularErrorException`() {

        // ARRANGE

        val ACCESS_TOKEN = null

        val USER_ID = "90453698768"
        val LIMIT = 0
        val OFFSET = 0

        whenever(userLocalDataSourceMock.getAccessToken()).thenReturn(ACCESS_TOKEN)

        val testObserver = TestObserver<Paging<PlaylistEntity>>()

        // ACT

        playlistRemoteDataSource.getPagedPlaylistsByUserId(USER_ID, LIMIT, OFFSET)
                .subscribe(testObserver)

        // ASSERT

        testObserver.assertNotComplete()
                .assertError(SpotifyRegularErrorException::class.java)
                .assertErrorMessage(UNAUTHORIZED_STATUS_MESSAGE)
    }

    @Test fun `Get paged playlists by user id, when there is page to be fetched, then return page with items`() {

        // ARRANGE

        val DATA_SET_FILE_NAME = "get_me_playlists.json"

        val EMITTED_VALUE_COUNT = 1

        val ACCESS_TOKEN = "k3dKssoamxMa20dj3Lzamk1La9ssamxLDfjsKE3d9dmxMxnzSKie20da0sk2"

        val USER_ID = "90453698768"
        val LIMIT = 10
        val OFFSET = 14

        val expectedPaging = DataUtils.createPagedPlaylistEntitiesTestData(FileUtils.readFile(DATA_SET_FILE_NAME))

        whenever(userLocalDataSourceMock.getAccessToken()).thenReturn(ACCESS_TOKEN)

        val testObserver = TestObserver<Paging<PlaylistEntity>>()

        // ACT

        playlistRemoteDataSource.getPagedPlaylistsByUserId(USER_ID, LIMIT, OFFSET)
                .subscribe(testObserver)

        // ASSERT

        testObserver
                .assertNoErrors()
                .assertComplete()
                .assertValueCount(EMITTED_VALUE_COUNT)
                .assertOf { paging ->
                    (paging == Single.just(expectedPaging))
                }
    }

    // endregion

    // region OVERRIDED METHODS

    override fun injectDependencies() {

        val testComponent = DaggerTestPlaylistDataSourceComponent.builder()
                .testPlaylistDataSourceModule(TestPlaylistDataSourceModule(mockWebServer))
                .build()

        testComponent.inject(this)
    }

    // endregion
}