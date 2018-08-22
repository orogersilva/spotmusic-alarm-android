package com.orogersilva.spotmusicalarm.dashboarddata.local

import com.orogersilva.spotmusicalarm.base.wrapper.SharedPreferencesWrapperContract
import com.orogersilva.spotmusicalarm.dashboarddata.contract.UserDataContract
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(private val sharedPreferencesWrapper: SharedPreferencesWrapperContract) : UserDataContract.Local {

    // region PROPERTIES

    private val ACCESS_TOKEN_PREF_KEY = "ACCESS_TOKEN_PREF_KEY"

    // endregion

    // region OVERRIDED METHODS

    override fun getAccessToken(): String? =
            sharedPreferencesWrapper.getString(ACCESS_TOKEN_PREF_KEY, null)

    override fun saveAccessToken(accessToken: String) {

        sharedPreferencesWrapper.putString(ACCESS_TOKEN_PREF_KEY, accessToken)
        sharedPreferencesWrapper.commit()
    }

    // endregion
}