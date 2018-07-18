package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.tracklist

import android.arch.lifecycle.ViewModel
import com.orogersilva.spotmusicalarm.base.scheduler.SchedulerProvider
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.TrackRepository
import javax.inject.Inject

class TrackListViewModel @Inject constructor(private val trackRepository: TrackRepository,
                                             private val schedulerProvider: SchedulerProvider) : ViewModel() {


}