package com.orogersilva.spotmusicalarm.dashboarddata.contract

import com.orogersilva.spotmusicalarm.dashboarddata.entity.UserEntity
import io.reactivex.Single

interface UserDataContract {

    // region INTERFACES

    interface Remote {

        // region METHODS

        fun getMe(): Single<UserEntity>

        // endregion
    }

    // endregion
}