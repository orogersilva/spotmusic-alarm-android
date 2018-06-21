package com.orogersilva.spotmusicalarm.featuredashboard

import dagger.android.support.DaggerApplication

interface ApplicationProvider {

    // region METHODS

    fun provideApplication(): DaggerApplication

    // endregion
}