package com.orogersilva.spotmusicalarm.dashboarddata.mapper

import com.orogersilva.spotmusicalarm.dashboarddata.dto.UserDTO
import com.orogersilva.spotmusicalarm.dashboarddata.entity.UserEntity

object UserMapper {

    // region PUBLIC METHODS

    fun transformUserDTOToUserEntity(userDTO: UserDTO): UserEntity =
            UserEntity(userDTO.id, userDTO.displayName)

    // endregion
}