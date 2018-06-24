package com.orogersilva.spotmusicalarm.dashboarddomain.repository

import com.orogersilva.spotmusicalarm.dashboarddomain.model.User
import io.reactivex.Single

interface UserRepository {

    // region METHODS

    fun getMe(): Single<User>

    // endregion
}