package com.orogersilva.spotmusicalarm.dashboarddomain.model

data class Paging<T>(val items: List<T>,
                     val limit: Int,
                     val next: String?,
                     val offset: Int,
                     val previous: String?,
                     val total: Int)