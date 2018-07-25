package com.orogersilva.spotmusicalarm.dashboarddata

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.orogersilva.spotmusicalarm.dashboarddata.entity.PlaylistEntity
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Paging

object DataUtils {

    // region PUBLIC METHODS

    fun createPagedPlaylistEntitiesTestData(jsonStr: String?): Paging<PlaylistEntity> {

        val listType = object : TypeToken<Paging<PlaylistEntity>>(){}.type

        val pagedPlaylistEntities = Gson().fromJson<Paging<PlaylistEntity>>(jsonStr, listType)

        return pagedPlaylistEntities
    }

    // endregion
}