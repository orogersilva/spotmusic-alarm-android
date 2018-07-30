package com.orogersilva.spotmusicalarm.dashboarddata.repository

import com.orogersilva.spotmusicalarm.dashboarddata.contract.AlarmDataContract
import javax.inject.Inject

class AlarmDataRepository @Inject constructor(private val alarmLocalDataSource: AlarmDataContract.Local,
                                              private val ) {
}

/*class UserDataRepository @Inject constructor(private val userLocalDataSource: UserDataContract.Local,
                                             private val userRemoteDataSource: UserDataContract.Remote) : UserRepository {

    // region OVERRIDED METHODS

    override fun getMe(): Single<User> =
            userRemoteDataSource.getMe()
                .flatMap { userEntity -> Single.just(UserMapper.transformUserEntityToUser(userEntity)) }

    override fun saveAccessToken(accessToken: String) {

        userLocalDataSource.saveAccessToken(accessToken)
    }

    // endregion
}*/