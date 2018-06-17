package com.orogersilva.spotmusicalarm.core.presentation.screen.newclockalarm

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class NewClockAlarmViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    // region OVERRIDED METHODS

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            NewClockAlarmViewModel() as T

    // endregion
}