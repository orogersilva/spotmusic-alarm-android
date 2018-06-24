package com.orogersilva.spotmusicalarm.dashboarddata.remote

import com.orogersilva.spotmusicalarm.dashboarddata.contract.UserDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.entity.UserEntity
import com.orogersilva.spotmusicalarm.dashboarddata.mapper.UserMapper
import com.orogersilva.spotmusicalarm.dashboarddata.remote.endpoint.UserProfileApiClient
import com.orogersilva.spotmusicalarm.dashboarddomain.local.LoginPreferencesDataSource
import io.reactivex.Single
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val userProfileApiClient: UserProfileApiClient) : UserDataContract.Remote {

    // region OVERRIDED METHODS

    override fun getMe(): Single<UserEntity> =
            userProfileApiClient.getMe()
                .flatMap { userDTO -> Single.just(UserMapper.transformUserDTOToUserEntity(userDTO)) }

    // endregion
}