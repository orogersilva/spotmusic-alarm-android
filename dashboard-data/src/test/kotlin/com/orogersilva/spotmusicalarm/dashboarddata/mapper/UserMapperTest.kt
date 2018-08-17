package com.orogersilva.spotmusicalarm.dashboarddata.mapper

import com.orogersilva.spotmusicalarm.dashboarddata.dto.UserDTO
import com.orogersilva.spotmusicalarm.dashboarddata.local.entity.UserEntity
import com.orogersilva.spotmusicalarm.dashboarddomain.model.User
import org.junit.Test
import kotlin.test.assertEquals

class UserMapperTest {

    // region TEST METHODS

    @Test fun `Transform UserDTO to UserEntity, when UserDTO is provided, then return UserEntity successfully`() {

        // ARRANGE

        val DISPLAY_NAME = "JM Wizzler"
        val HREF = "https://api.spotify.com/v1/users/wizzler"
        val ID = "wizzler"
        val TYPE = "user"
        val URI = "spotify:user:wizzler"

        val userDTO = UserDTO(
            DISPLAY_NAME,
            HREF,
            ID,
            TYPE,
            URI
        )

        val expectedUserEntity = UserEntity(ID, DISPLAY_NAME)

        // ACT

        val userEntity = UserMapper.transformUserDTOToUserEntity(userDTO)

        // ASSERT

        assertEquals(expectedUserEntity, userEntity)
    }

    @Test fun `Transform UserEntity to User, when UserEntity is provided, then return User successfully`() {

        // ARRANGE

        val ID = "wizzler"
        val DISPLAY_NAME = "JM Wizzler"

        val userEntity = UserEntity(ID, DISPLAY_NAME)

        val expectedUser = User(ID)

        // ACT

        val user = UserMapper.transformUserEntityToUser(userEntity)

        // ASSERT

        assertEquals(expectedUser, user)
    }

    // endregion
}