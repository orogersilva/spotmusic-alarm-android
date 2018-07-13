package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.clockalarmmanager

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ClockAlarmManagerViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    // region OVERRIDED METHODS

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) : T =
            ClockAlarmManagerViewModel() as T

    // endregion
}