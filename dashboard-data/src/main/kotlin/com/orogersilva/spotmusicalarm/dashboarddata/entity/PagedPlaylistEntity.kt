package com.orogersilva.spotmusicalarm.dashboarddata.entity

data class PagedPlaylistEntity(val items: List<PlaylistEntity>,
                               val limit: Int,
                               val next: String?,
                               val offset: Int,
                               val previous: String?,
                               val total: Int)