package com.orogersilva.spotmusicalarm.dashboarddata.pagination

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.ItemKeyedDataSource
import com.orogersilva.spotmusicalarm.base.scheduler.SchedulerProvider
import com.orogersilva.spotmusicalarm.dashboarddata.contract.PlaylistDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.contract.UserDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.entity.PlaylistEntity
import com.orogersilva.spotmusicalarm.dashboarddata.mapper.PlaylistMapper
import com.orogersilva.spotmusicalarm.dashboarddomain.enums.NetworkState
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Paging
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Playlist
import javax.inject.Inject

class PlaylistPaginationDataSource @Inject constructor(private val playlistRemoteDataSource: PlaylistDataContract.Remote,
                                                       private val userRemoteDataSource: UserDataContract.Remote,
                                                       private val schedulerProvider: SchedulerProvider)
    : ItemKeyedDataSource<Long, Playlist>() {

    // region PROPERTIES

    val networkStateLiveData = MutableLiveData<NetworkState>()
    val initialLoadingLiveData = MutableLiveData<NetworkState>()

    private var currentOffset = 0L
    private var pagesTotal = 0L

    // endregion

    // region OVERRIDED METHODS

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Playlist>) {

        networkStateLiveData.postValue(NetworkState.LOADING)
        initialLoadingLiveData.postValue(NetworkState.LOADING)

        userRemoteDataSource.getMe()
                .flatMap { userEntity ->
                    playlistRemoteDataSource.getPagedPlaylistsByUserId(
                            userEntity.id, params.requestedLoadSize, currentOffset.toInt())
                }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                        { paging -> onPlaylistsFetched(paging, callback) },
                        { throwable -> onError(throwable) }
                )
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Playlist>) {

        if (currentOffset < pagesTotal) {

            networkStateLiveData.postValue(NetworkState.LOADING)

            userRemoteDataSource.getMe()
                    .flatMap { userEntity ->
                        playlistRemoteDataSource.getPagedPlaylistsByUserId(
                                userEntity.id, params.requestedLoadSize, currentOffset.toInt())
                    }
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribe(
                            { paging -> onMorePlaylistsFetched(paging, callback) },
                            { throwable -> onPaginationError(throwable) }
                    )

        } else {

            networkStateLiveData.postValue(NetworkState.SUCCESS)
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Playlist>) {
    }

    override fun getKey(playlist: Playlist): Long = currentOffset

    // endregion

    // region UTILITY METHODS

    private fun onPlaylistsFetched(paging: Paging<PlaylistEntity>, callback: LoadInitialCallback<Playlist>) {

        initialLoadingLiveData.postValue(NetworkState.SUCCESS)

        currentOffset += paging.items.size
        pagesTotal += paging.total

        callback.onResult(PlaylistMapper.transformPlaylistEntitiesToPlaylists(paging.items))
    }

    private fun onMorePlaylistsFetched(paging: Paging<PlaylistEntity>, callback: LoadCallback<Playlist>) {

        networkStateLiveData.postValue(NetworkState.SUCCESS)

        currentOffset += paging.items.size

        callback.onResult(PlaylistMapper.transformPlaylistEntitiesToPlaylists(paging.items))
    }

    private fun onError(throwable: Throwable) {

        initialLoadingLiveData.postValue(NetworkState.FAILED)
    }

    private fun onPaginationError(throwable: Throwable) {

        networkStateLiveData.postValue(NetworkState.FAILED)
    }

    // endregion
}