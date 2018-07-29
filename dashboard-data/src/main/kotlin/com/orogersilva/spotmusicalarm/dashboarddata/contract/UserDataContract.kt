package com.orogersilva.spotmusicalarm.dashboarddata.contract

import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.UserEntity
import io.reactivex.Single

interface UserDataContract {

    // region INTERFACES

    interface Local {

        // region METHODS

        fun getAccessToken(): String?

        fun saveAccessToken(accessToken: String)

        // endregion
    }

    interface Remote {

        // region METHODS

        fun getMe(): Single<UserEntity>

        // endregion
    }

    // endregion
}