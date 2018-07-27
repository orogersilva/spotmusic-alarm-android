package com.orogersilva.spotmusicalarm.dashboarddata.factory

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.orogersilva.spotmusicalarm.dashboarddata.pagination.PlaylistPaginationDataSource
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Playlist
import javax.inject.Inject

class PlaylistDataSourceFactory @Inject constructor(private val playlistPaginationDataSource: PlaylistPaginationDataSource)
    : DataSource.Factory<Long, Playlist>() {

    // region PROPERTIES

    val playlistPaginationDataSourceLiveData = MutableLiveData<PlaylistPaginationDataSource>()

    // endregion

    // region OVERRIDED METHODS

    override fun create(): DataSource<Long, Playlist> {

        playlistPaginationDataSourceLiveData.postValue(playlistPaginationDataSource)

        return playlistPaginationDataSource
    }

    // endregion
}