package com.orogersilva.spotmusicalarm.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class DashboardViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    // region OVERRIDED METHODS

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) : T =
            DashboardViewModel() as T

    // endregion
}