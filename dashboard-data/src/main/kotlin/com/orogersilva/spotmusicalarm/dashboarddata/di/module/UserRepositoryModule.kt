package com.orogersilva.spotmusicalarm.dashboarddata.di.module

import android.content.SharedPreferences
import com.orogersilva.spotmusicalarm.base.di.scope.ActivityScope
import com.orogersilva.spotmusicalarm.dashboarddata.BuildConfig
import com.orogersilva.spotmusicalarm.dashboarddata.contract.UserDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.local.UserLocalDataSource
import com.orogersilva.spotmusicalarm.dashboarddata.remote.UserRemoteDataSource
import com.orogersilva.spotmusicalarm.dashboarddata.remote.endpoint.UserApiClient
import com.orogersilva.spotmusicalarm.dashboarddata.repository.UserDataRepository
import com.orogersilva.spotmusicalarm.dashboarddomain.di.scope.DashboardScope
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.UserRepository
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
open class UserRepositoryModule {

    // region PROVIDERS

    @Provides @DashboardScope open fun provideUserLocalDataSource(sharedPreferences: SharedPreferences,
                                                                            sharedPreferencesEditor: SharedPreferences.Editor): UserDataContract.Local =
            UserLocalDataSource(sharedPreferences, sharedPreferencesEditor)

    @Provides @DashboardScope open fun provideOkHttpClient(userLocalDataSource: UserDataContract.Local): OkHttpClient {

        val okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(object : Interceptor {

                    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
                    override fun intercept(chain: Interceptor.Chain): Response {

                        val accessToken = userLocalDataSource.getAccessToken()

                        var request: Request? = null

                        if (accessToken == null) {

                            request = chain.request()
                                    .newBuilder()
                                    .build()

                        } else {

                            request = chain.request()
                                    .newBuilder()
                                    .addHeader("Authorization", accessToken)
                                    .build()
                        }

                        return chain.proceed(request)
                    }
                })
                .build()

        return okHttpClient
    }

    @Provides @DashboardScope open fun provideUserApiClient(okHttpClient: OkHttpClient): UserApiClient {

        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.SPOTIFY_API_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(UserApiClient::class.java)
    }

    @Provides @DashboardScope open fun provideUserRemoteDataSource(userApiClient: UserApiClient): UserDataContract.Remote =
            UserRemoteDataSource(userApiClient)

    @Provides @DashboardScope open fun provideUserRepository(userLocalDataSource: UserDataContract.Local,
                                                                 userRemoteDataSource: UserDataContract.Remote): UserRepository =
            UserDataRepository(userLocalDataSource, userRemoteDataSource)

    // endregion
}