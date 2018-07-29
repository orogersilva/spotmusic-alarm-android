package com.orogersilva.spotmusicalarm.dashboarddata.pagination

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.ItemKeyedDataSource
import com.orogersilva.spotmusicalarm.base.scheduler.SchedulerProvider
import com.orogersilva.spotmusicalarm.dashboarddata.contract.PlaylistDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.contract.UserDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.mapper.TrackMapper
import com.orogersilva.spotmusicalarm.dashboarddata.local.relation.TrackAndAllArtists
import com.orogersilva.spotmusicalarm.dashboarddomain.enums.NetworkState
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Paging
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Track
import javax.inject.Inject

class TrackPaginationDataSource @Inject constructor(private val playlistRemoteDataSource: PlaylistDataContract.Remote,
                                                    private val userRemoteDataSource: UserDataContract.Remote,
                                                    private val schedulerProvider: SchedulerProvider,
                                                    private val playlistId: String)
    : ItemKeyedDataSource<Long, Track>() {

    // region PROPERTIES

    val networkStateLiveData = MutableLiveData<NetworkState>()
    val initialLoadingLiveData = MutableLiveData<NetworkState>()

    private var currentOffset = 0L
    private var pagesTotal = 0L

    // endregion

    // region OVERRIDED METHODS

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Track>) {

        networkStateLiveData.postValue(NetworkState.LOADING)
        initialLoadingLiveData.postValue(NetworkState.LOADING)

        userRemoteDataSource.getMe()
                .flatMap { userEntity ->
                    playlistRemoteDataSource.getPagedTrackAndAllArtistsFromPlaylist(
                            userEntity.id, playlistId, params.requestedLoadSize, currentOffset.toInt())
                }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                        { paging -> onTrackAndAllArtistsFetched(paging, callback) },
                        { throwable -> onError(throwable) }
                )
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Track>) {

        if (currentOffset < pagesTotal) {

            networkStateLiveData.postValue(NetworkState.LOADING)

            userRemoteDataSource.getMe()
                    .flatMap { userEntity ->
                        playlistRemoteDataSource.getPagedTrackAndAllArtistsFromPlaylist(
                                userEntity.id, playlistId, params.requestedLoadSize, currentOffset.toInt())
                    }
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribe(
                            { paging -> onMoreTrackAndAllArtistsFetched(paging, callback) },
                            { throwable -> onPaginationError(throwable) }
                    )
        } else {

            networkStateLiveData.postValue(NetworkState.SUCCESS)
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Track>) {
    }

    override fun getKey(item: Track): Long = currentOffset

    // endregion

    // region UTILITY METHODS

    private fun onTrackAndAllArtistsFetched(paging: Paging<TrackAndAllArtists>, callback: LoadInitialCallback<Track>) {

        initialLoadingLiveData.postValue(NetworkState.SUCCESS)

        currentOffset += paging.items.size
        pagesTotal += paging.total

        callback.onResult(TrackMapper.transformTrackAndAllArtistsListToTracks(paging.items))
    }

    private fun onMoreTrackAndAllArtistsFetched(paging: Paging<TrackAndAllArtists>, callback: LoadCallback<Track>) {

        networkStateLiveData.postValue(NetworkState.SUCCESS)

        currentOffset += paging.items.size

        callback.onResult(TrackMapper.transformTrackAndAllArtistsListToTracks(paging.items))
    }

    private fun onError(throwable: Throwable) {

        initialLoadingLiveData.postValue(NetworkState.FAILED)
    }

    private fun onPaginationError(throwable: Throwable) {

        networkStateLiveData.postValue(NetworkState.FAILED)
    }

    // endregion
}