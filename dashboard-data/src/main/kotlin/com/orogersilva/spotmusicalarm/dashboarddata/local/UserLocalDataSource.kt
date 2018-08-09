package com.orogersilva.spotmusicalarm.dashboarddata.local

import android.content.SharedPreferences
import com.orogersilva.spotmusicalarm.dashboarddata.contract.UserDataContract
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(
        private val sharedPreferences: SharedPreferences,
        private val sharedPreferencesEditor: SharedPreferences.Editor
) : UserDataContract.Local {

    // region PROPERTIES

    private val ACCESS_TOKEN_PREF_KEY = "ACCESS_TOKEN_PREF_KEY"

    // endregion

    // region OVERRIDED METHODS

    override fun getAccessToken(): String? =
            sharedPreferences.getString(ACCESS_TOKEN_PREF_KEY, null)

    override fun saveAccessToken(accessToken: String) {

        sharedPreferencesEditor.putString(ACCESS_TOKEN_PREF_KEY, accessToken)
        sharedPreferencesEditor.commit()
    }

    // endregion
}