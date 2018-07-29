package com.orogersilva.spotmusicalarm.dashboarddata.mapper

import com.orogersilva.spotmusicalarm.dashboarddata.dto.UserDTO
import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.UserEntity
import com.orogersilva.spotmusicalarm.dashboarddomain.model.User

object UserMapper {

    // region PUBLIC METHODS

    fun transformUserDTOToUserEntity(userDTO: UserDTO): UserEntity =
            UserEntity(userDTO.id, userDTO.displayName)

    fun transformUserEntityToUser(userEntity: UserEntity): User =
            User(userEntity.id)

    // endregion
}