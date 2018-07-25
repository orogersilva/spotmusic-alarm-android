package com.orogersilva.spotmusicalarm.dashboarddata.di.module

import com.orogersilva.spotmusicalarm.base.di.scope.ActivityScope
import com.orogersilva.spotmusicalarm.base.scheduler.SchedulerProvider
import com.orogersilva.spotmusicalarm.dashboarddata.BuildConfig
import com.orogersilva.spotmusicalarm.dashboarddata.contract.PlaylistDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.factory.PlaylistDataSourceFactory
import com.orogersilva.spotmusicalarm.dashboarddata.pagination.PlaylistPaginationDataSource
import com.orogersilva.spotmusicalarm.dashboarddata.remote.PlaylistRemoteDataSource
import com.orogersilva.spotmusicalarm.dashboarddata.remote.endpoint.PlaylistApiClient
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.PlaylistRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
open class PlaylistDataSourceModule {

    // region PROVIDERS

    @Provides @ActivityScope open fun providePlaylistApiClient(okHttpClient: OkHttpClient): PlaylistApiClient {

        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.SPOTIFY_API_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(PlaylistApiClient::class.java)
    }

    @Provides @ActivityScope open fun providePlaylistRemoteDataSource(playlistApiClient: PlaylistApiClient): PlaylistDataContract.Remote =
            PlaylistRemoteDataSource(playlistApiClient)

    @Provides @ActivityScope open fun providePlaylistPaginationDataSource(playlistRemoteDataSource: PlaylistRemoteDataSource,
                                                                          schedulerProvider: SchedulerProvider): PlaylistPaginationDataSource =
            PlaylistPaginationDataSource(playlistRemoteDataSource, schedulerProvider)

    @Provides @ActivityScope open fun providePlaylistDataSourceFactory(playlistPaginationDataSource: PlaylistPaginationDataSource): PlaylistDataSourceFactory =
            PlaylistDataSourceFactory(playlistPaginationDataSource)

    // endregion
}