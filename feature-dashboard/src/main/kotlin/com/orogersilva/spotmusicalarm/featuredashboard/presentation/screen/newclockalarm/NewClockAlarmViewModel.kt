package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.newclockalarm

import android.arch.lifecycle.ViewModel
import com.orogersilva.spotmusicalarm.featuredashboard.shared.SingleLiveEvent

class NewClockAlarmViewModel : ViewModel() {

    // region PROPERTIES

    val setClockAlarmMusicEvent = SingleLiveEvent<Void>()

    // endregion

    // region PUBLIC METHODS

    fun setClockAlarmTime() {


    }

    fun setClockAlarmMusic() {

        setClockAlarmMusicEvent.call()
    }

    fun saveClockAlarmSettings() {


    }

    // endregion
}