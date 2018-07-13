package com.orogersilva.spotmusicalarm.dashboarddata.di.module

import android.content.Context
import android.content.SharedPreferences
import com.orogersilva.spotmusicalarm.dashboarddata.di.scope.DashboardScope
import dagger.Module
import dagger.Provides

@Module
object PreferencesModule {

    // region PROPERTIES

    private val PREF_FILE_KEY = "com.orogersilva.spotmusicalarm.dashboarddata.PREF_FILE_KEY"

    // endregion

    // region PROVIDERS

    @Provides @DashboardScope @JvmStatic fun provideSharedPreferences(context: Context): SharedPreferences =
            context.getSharedPreferences(PREF_FILE_KEY, Context.MODE_PRIVATE)

    @Provides @DashboardScope @JvmStatic fun provideSharedPreferencesEditor(sharedPreferences: SharedPreferences): SharedPreferences.Editor =
            sharedPreferences.edit()

    // endregion
}