package com.orogersilva.spotmusicalarm.base.wrapper.impl

import android.content.SharedPreferences
import com.orogersilva.spotmusicalarm.base.wrapper.SharedPreferencesWrapperContract
import javax.inject.Inject

class SharedPreferencesWrapper @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val sharedPreferencesEditor: SharedPreferences.Editor
) : SharedPreferencesWrapperContract {

    override fun getString(key: String, defaultValue: String?): String =
        sharedPreferences.getString(key, defaultValue)

    override fun putString(key: String, value: String?) {

        sharedPreferencesEditor.putString(key, value)
    }

    override fun commit(): Boolean =
        sharedPreferencesEditor.commit()
}