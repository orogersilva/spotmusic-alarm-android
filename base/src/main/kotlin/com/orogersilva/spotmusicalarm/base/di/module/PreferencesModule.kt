package com.orogersilva.spotmusicalarm.base.di.module

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.orogersilva.spotmusicalarm.base.wrapper.SharedPreferencesWrapperContract
import com.orogersilva.spotmusicalarm.base.wrapper.impl.SharedPreferencesWrapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class PreferencesModule {

    // region PROPERTIES

    private val PREF_FILE_KEY = "com.orogersilva.spotmusicalarm.base.PREF_FILE_KEY"

    // endregion

    // region PROVIDERS

    @SuppressLint("CommitPrefEdits")
    @Provides @Singleton open fun provideSharedPreferencesWrapper(context: Context) : SharedPreferencesWrapperContract {

        val sharedPreferences = context.getSharedPreferences(PREF_FILE_KEY, Context.MODE_PRIVATE)
        val sharedPreferencesEditor = sharedPreferences.edit()

        return SharedPreferencesWrapper(sharedPreferences, sharedPreferencesEditor)
    }

    // endregion
}