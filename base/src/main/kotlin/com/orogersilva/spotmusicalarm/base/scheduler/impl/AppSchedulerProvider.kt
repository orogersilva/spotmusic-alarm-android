package com.orogersilva.spotmusicalarm.base.scheduler.impl

import com.orogersilva.spotmusicalarm.base.scheduler.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppSchedulerProvider : SchedulerProvider {

    // region OVERRIDED METHODS

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

    override fun io(): Scheduler = Schedulers.io()

    // endregion
}