package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.playlist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.orogersilva.spotmusicalarm.dashboarddata.factory.PlaylistDataSourceFactory
import com.orogersilva.spotmusicalarm.dashboarddata.shared.SingleLiveEvent
import com.orogersilva.spotmusicalarm.dashboarddomain.enums.NetworkState
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Playlist
import javax.inject.Inject

class PlaylistViewModel @Inject constructor(playlistDataSourceFactory: PlaylistDataSourceFactory) : ViewModel() {

    // region PROPERTIES

    val initialLoadingLiveData: LiveData<NetworkState>
    val networkStateLiveData: LiveData<NetworkState>
    val playlistPagedListLiveData: LiveData<PagedList<Playlist>>

    val selectedPlaylistSingleEvent = SingleLiveEvent<String>()

    // endregion

    // region INITIALIZER BLOCK

    init {

        initialLoadingLiveData = Transformations.switchMap(playlistDataSourceFactory.playlistPaginationDataSourceLiveData) {
            playlistPaginationDataSource -> playlistPaginationDataSource.initialLoadingLiveData
        }

        networkStateLiveData = Transformations.switchMap(playlistDataSourceFactory.playlistPaginationDataSourceLiveData) {
            playlistPaginationDataSource -> playlistPaginationDataSource.networkStateLiveData
        }

        val playlistPagingConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(10)
                .setInitialLoadSizeHint(20)
                .build()

        playlistPagedListLiveData = LivePagedListBuilder(playlistDataSourceFactory, playlistPagingConfig)
                .build()
    }

    // endregion

    // region PUBLIC METHODS

    fun resume() {
    }

    fun selectPlaylist(id: String) {

        selectedPlaylistSingleEvent.value = id
    }

    // endregion
}