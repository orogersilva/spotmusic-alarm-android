package com.orogersilva.spotmusicalarm.core

import dagger.android.support.DaggerApplication

interface ApplicationProvider {

    // region METHODS

    fun provideApplication(): DaggerApplication

    // endregion
}