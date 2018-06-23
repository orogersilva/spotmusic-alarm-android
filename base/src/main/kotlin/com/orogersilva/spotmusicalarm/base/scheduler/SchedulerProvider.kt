package com.orogersilva.spotmusicalarm.base.scheduler

import io.reactivex.Scheduler

interface SchedulerProvider {

    // region METHODS

    fun ui(): Scheduler

    fun io(): Scheduler

    // endregion
}