package com.orogersilva.spotmusicalarm.dashboarddata.local.entity

data class PlaylistPagingEntity(val items: List<PlaylistEntity>,
                                val limit: Int,
                                val next: String?,
                                val offset: Int,
                                val previous: String?,
                                val total: Int)