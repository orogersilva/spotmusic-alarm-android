package com.orogersilva.spotmusicalarm.dashboarddata.repository

import com.orogersilva.spotmusicalarm.dashboarddata.contract.UserDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.mapper.UserMapper
import com.orogersilva.spotmusicalarm.dashboarddomain.model.User
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class UserDataRepository @Inject constructor(
    private val userLocalDataSource: UserDataContract.Local,
    private val userRemoteDataSource: UserDataContract.Remote
) : UserRepository {

    // region OVERRIDED METHODS

    override fun getMe(): Single<User> =
            userRemoteDataSource.getMe()
                .flatMap { userEntity -> Single.just(UserMapper.transformUserEntityToUser(userEntity)) }

    override fun saveAccessToken(accessToken: String) {

        userLocalDataSource.saveAccessToken(accessToken)
    }

    // endregion
}