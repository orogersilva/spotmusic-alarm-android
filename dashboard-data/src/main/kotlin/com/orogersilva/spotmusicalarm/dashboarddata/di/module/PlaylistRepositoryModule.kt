package com.orogersilva.spotmusicalarm.dashboarddata.di.module

import com.orogersilva.spotmusicalarm.base.di.scope.ActivityScope
import com.orogersilva.spotmusicalarm.dashboarddata.BuildConfig
import com.orogersilva.spotmusicalarm.dashboarddata.contract.PlaylistDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.contract.UserDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.remote.PlaylistRemoteDataSource
import com.orogersilva.spotmusicalarm.dashboarddata.remote.endpoint.PlaylistApiClient
import com.orogersilva.spotmusicalarm.dashboarddata.repository.PlaylistDataRepository
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.PlaylistRepository
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
object PlaylistRepositoryModule {

    // region PROVIDERS

    @Provides @ActivityScope @JvmStatic fun provideOkHttpClient(userLocalDataSource: UserDataContract.Local): OkHttpClient {

        val okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(object : Interceptor {

                    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
                    override fun intercept(chain: Interceptor.Chain): Response {

                        val accessToken = userLocalDataSource.getAccessToken()

                        var request: Request? = null

                        accessToken?.let {

                            request = chain.request()
                                    .newBuilder()
                                    .addHeader("Authorization", it)
                                    .build()
                        }

                        return chain.proceed(request)
                    }
                })
                .build()

        return okHttpClient
    }

    @Provides @ActivityScope @JvmStatic fun providePlaylistApiClient(okHttpClient: OkHttpClient): PlaylistApiClient {

        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.SPOTIFY_API_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(PlaylistApiClient::class.java)
    }

    @Provides @ActivityScope @JvmStatic fun providePlaylistRemoteDataSource(playlistApiClient: PlaylistApiClient): PlaylistDataContract.Remote =
            PlaylistRemoteDataSource(playlistApiClient)

    @Provides @ActivityScope @JvmStatic fun providePlaylistRepository(playlistRemoteDataSource: PlaylistDataContract.Remote): PlaylistRepository =
            PlaylistDataRepository(playlistRemoteDataSource)

    // endregion
}