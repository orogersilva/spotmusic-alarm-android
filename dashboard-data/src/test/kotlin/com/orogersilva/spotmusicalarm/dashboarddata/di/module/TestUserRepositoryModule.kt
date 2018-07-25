package com.orogersilva.spotmusicalarm.dashboarddata.di.module

import com.nhaarman.mockitokotlin2.mock
import com.orogersilva.spotmusicalarm.dashboarddata.contract.UserDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.remote.UserRemoteDataSource
import com.orogersilva.spotmusicalarm.dashboarddata.remote.endpoint.UserApiClient
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
open class TestUserRepositoryModule(private val mockWebServer: MockWebServer) {

    // region PROVIDERS

    @Provides @Singleton open fun provideUserLocalDataSource(): UserDataContract.Local = mock()

    @Provides @Singleton open fun provideOkHttpClient(userLocalDataSource: UserDataContract.Local): OkHttpClient {

        val okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(object : Interceptor {

                    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
                    override fun intercept(chain: Interceptor.Chain): Response {

                        val accessToken = userLocalDataSource.getAccessToken()

                        var request: Request?

                        if (accessToken == null) {

                            request = chain.request()
                                    .newBuilder()
                                    .build()

                        } else {

                            request = chain.request()
                                    .newBuilder()
                                    .addHeader("Authorization", "Bearer " + accessToken)
                                    .build()
                        }

                        return chain.proceed(request)
                    }
                })
                .build()

        return okHttpClient
    }

    @Provides @Singleton fun provideUserApiClient(okHttpClient: OkHttpClient): UserApiClient {

        val retrofit = Retrofit.Builder()
                .baseUrl(mockWebServer.url("/").toString())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(UserApiClient::class.java)
    }

    @Provides @Singleton open fun provideUserRemoteDataSource(userApiClient: UserApiClient): UserDataContract.Remote =
            UserRemoteDataSource(userApiClient)

    // endregion
}