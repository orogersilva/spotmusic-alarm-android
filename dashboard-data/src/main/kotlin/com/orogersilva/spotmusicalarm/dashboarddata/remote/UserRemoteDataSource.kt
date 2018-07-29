package com.orogersilva.spotmusicalarm.dashboarddata.remote

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.orogersilva.spotmusicalarm.dashboarddata.contract.UserDataContract
import com.orogersilva.spotmusicalarm.dashboarddata.dto.SpotifyRegularErrorDTO
import com.orogersilva.spotmusicalarm.dashboarddata.dto.UserDTO
import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.UserEntity
import com.orogersilva.spotmusicalarm.dashboarddata.mapper.UserMapper
import com.orogersilva.spotmusicalarm.dashboarddata.remote.endpoint.UserApiClient
import com.orogersilva.spotmusicalarm.dashboarddomain.SpotifyRegularErrorException
import io.reactivex.Single
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val userApiClient: UserApiClient) : UserDataContract.Remote {

    // region OVERRIDED METHODS

    override fun getMe(): Single<UserEntity> =
            userApiClient.getMe()
                .flatMap { userHttpResponse ->

                    when (userHttpResponse.code()) {

                        OK_STATUS_CODE -> {

                            val content = userHttpResponse.body()?.string()

                            val type = object : TypeToken<UserDTO>(){}.type

                            val userDTO = Gson().fromJson<UserDTO>(content, type)

                            Single.just(UserMapper.transformUserDTOToUserEntity(userDTO))
                        }

                        else -> {

                            val content = userHttpResponse.errorBody()?.string()

                            val type = object : TypeToken<SpotifyRegularErrorDTO>(){}.type

                            val spotifyRegularErrorDTO = Gson().fromJson<SpotifyRegularErrorDTO>(content, type)

                            Single.error(
                                    SpotifyRegularErrorException(
                                            spotifyRegularErrorDTO.error.status,
                                            spotifyRegularErrorDTO.error.message
                                    )
                            )
                        }
                    }
                }

    // endregion
}