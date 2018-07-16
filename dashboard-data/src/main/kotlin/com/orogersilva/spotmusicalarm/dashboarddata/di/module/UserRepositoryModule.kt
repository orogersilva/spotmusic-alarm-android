package com.orogersilva.spotmusicalarm.dashboarddata.di.module

import android.content.SharedPreferences
import com.orogersilva.spotmusicalarm.base.di.scope.ActivityScope
import com.orogersilva.spotmusicalarm.dashboarddata.BuildConfig
import com.orogersilva.spotmusicalarm.dashboarddata.contract.UserDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.local.UserLocalDataSource
import com.orogersilva.spotmusicalarm.dashboarddata.remote.RestClient
import com.orogersilva.spotmusicalarm.dashboarddata.remote.UserRemoteDataSource
import com.orogersilva.spotmusicalarm.dashboarddata.remote.endpoint.UserApiClient
import com.orogersilva.spotmusicalarm.dashboarddata.repository.UserDataRepository
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
object UserRepositoryModule {

    // region PROVIDERS

    @Provides @ActivityScope @JvmStatic fun provideUserLocalDataSource(sharedPreferences: SharedPreferences,
                                                                       sharedPreferencesEditor: SharedPreferences.Editor): UserDataContract.Local =
            UserLocalDataSource(sharedPreferences, sharedPreferencesEditor)

    @Provides @ActivityScope @JvmStatic fun provideUserRemoteDataSource(): UserDataContract.Remote =
            UserRemoteDataSource(RestClient.getApiClient(UserApiClient::class.java,
                BuildConfig.SPOTIFY_API_URL))

    @Provides @ActivityScope @JvmStatic fun provideUserRepository(userLocalDataSource: UserDataContract.Local,
                                                                  userRemoteDataSource: UserDataContract.Remote): UserRepository =
            UserDataRepository(userLocalDataSource, userRemoteDataSource)

    // endregion
}