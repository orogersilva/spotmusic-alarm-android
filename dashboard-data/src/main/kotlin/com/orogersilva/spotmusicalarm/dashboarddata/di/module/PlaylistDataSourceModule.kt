package com.orogersilva.spotmusicalarm.dashboarddata.di.module

import com.orogersilva.spotmusicalarm.base.scheduler.SchedulerProvider
import com.orogersilva.spotmusicalarm.dashboarddata.BuildConfig
import com.orogersilva.spotmusicalarm.dashboarddata.contract.PlaylistDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.contract.UserDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.factory.PlaylistDataSourceFactory
import com.orogersilva.spotmusicalarm.dashboarddata.pagination.PlaylistPaginationDataSource
import com.orogersilva.spotmusicalarm.dashboarddata.remote.PlaylistRemoteDataSource
import com.orogersilva.spotmusicalarm.dashboarddata.remote.endpoint.PlaylistApiClient
import com.orogersilva.spotmusicalarm.dashboarddomain.di.scope.DashboardScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
open class PlaylistDataSourceModule {

    // region PROVIDERS

    @Provides @DashboardScope open fun providePlaylistApiClient(okHttpClient: OkHttpClient): PlaylistApiClient {

        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.SPOTIFY_API_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(PlaylistApiClient::class.java)
    }

    @Provides @DashboardScope open fun providePlaylistRemoteDataSource(playlistApiClient: PlaylistApiClient): PlaylistDataContract.Remote =
            PlaylistRemoteDataSource(playlistApiClient)

    @Provides @DashboardScope open fun providePlaylistPaginationDataSource(
            playlistRemoteDataSource: PlaylistDataContract.Remote,
            userRemoteDataSource: UserDataContract.Remote,
            schedulerProvider: SchedulerProvider
    ): PlaylistPaginationDataSource =
            PlaylistPaginationDataSource(playlistRemoteDataSource, userRemoteDataSource, schedulerProvider)

    @Provides @DashboardScope open fun providePlaylistDataSourceFactory(playlistPaginationDataSource: PlaylistPaginationDataSource): PlaylistDataSourceFactory =
            PlaylistDataSourceFactory(playlistPaginationDataSource)

    // endregion
}