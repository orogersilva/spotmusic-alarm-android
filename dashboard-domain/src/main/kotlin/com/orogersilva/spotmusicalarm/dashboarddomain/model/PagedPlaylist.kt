package com.orogersilva.spotmusicalarm.dashboarddomain.model

data class PagedPlaylist(val items: List<Playlist>,
                         val limit: Int,
                         val next: String?,
                         val offset: Int,
                         val previous: String?,
                         val total: Int)