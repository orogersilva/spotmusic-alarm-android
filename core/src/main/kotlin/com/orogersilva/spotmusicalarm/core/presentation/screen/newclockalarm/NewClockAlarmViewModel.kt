package com.orogersilva.spotmusicalarm.core.presentation.screen.newclockalarm

import android.arch.lifecycle.ViewModel
import com.orogersilva.spotmusicalarm.core.shared.SingleLiveEvent

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