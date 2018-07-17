package com.orogersilva.spotmusicalarm.testutils.scheduler.impl

import com.orogersilva.spotmusicalarm.base.scheduler.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestSchedulerProvider : SchedulerProvider {

    // region OVERRIDED METHODS

    override fun ui(): Scheduler = Schedulers.trampoline()

    override fun io(): Scheduler = Schedulers.trampoline()

    // endregion
}