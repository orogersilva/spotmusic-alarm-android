package com.orogersilva.spotmusicalarm.base.di.module

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class PreferencesModule {

    // region PROPERTIES

    private val PREF_FILE_KEY = "com.orogersilva.spotmusicalarm.base.PREF_FILE_KEY"

    // endregion

    // region PROVIDERS

    @Provides @Singleton open fun provideSharedPreferences(context: Context): SharedPreferences =
            context.getSharedPreferences(PREF_FILE_KEY, Context.MODE_PRIVATE)

    @Provides @Singleton open fun provideSharedPreferencesEditor(sharedPreferences: SharedPreferences): SharedPreferences.Editor =
            sharedPreferences.edit()

    // endregion
}