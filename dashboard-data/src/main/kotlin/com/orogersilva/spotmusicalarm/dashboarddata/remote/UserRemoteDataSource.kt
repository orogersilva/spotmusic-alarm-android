package com.orogersilva.spotmusicalarm.dashboarddata.remote

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.orogersilva.spotmusicalarm.dashboarddata.contract.UserDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.dto.UserDTO
import com.orogersilva.spotmusicalarm.dashboarddata.entity.UserEntity
import com.orogersilva.spotmusicalarm.dashboarddata.mapper.UserMapper
import com.orogersilva.spotmusicalarm.dashboarddata.remote.endpoint.UserProfileApiClient
import com.orogersilva.spotmusicalarm.dashboarddomain.SpotifyException
import com.orogersilva.spotmusicalarm.dashboarddomain.local.LoginPreferencesDataSource
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val userProfileApiClient: UserProfileApiClient) : UserDataContract.Remote {

    // region OVERRIDED METHODS

    override fun getMe(): Single<UserEntity> {

        return userProfileApiClient.getMe()
                .flatMap { userProfileHttpResponse ->

                    when (userProfileHttpResponse.code()) {

                        OK_STATUS_CODE -> {

                            val content = userProfileHttpResponse.body()?.string()

                            val type = object : TypeToken<UserDTO>(){}.type

                            val userDTO = Gson().fromJson<UserDTO>(content, type)

                            Single.just(UserMapper.transformUserDTOToUserEntity(userDTO))
                        }

                        else -> {

                            Single.error(
                                    SpotifyException(userProfileHttpResponse.code(),
                                            userProfileHttpResponse.message())
                            )
                        }
                    }
                }
    }

    // endregion
}