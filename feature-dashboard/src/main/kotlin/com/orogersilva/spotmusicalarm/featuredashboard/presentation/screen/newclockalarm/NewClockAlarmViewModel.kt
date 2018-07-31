package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.newclockalarm

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.orogersilva.spotmusicalarm.base.scheduler.SchedulerProvider
import com.orogersilva.spotmusicalarm.dashboarddata.shared.SingleLiveEvent
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Track
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.AlarmRepository
import com.orogersilva.spotmusicalarm.dashboarddomain.repository.UserRepository
import java.util.*
import javax.inject.Inject

class NewClockAlarmViewModel @Inject constructor(private val alarmRepository: AlarmRepository,
                                                 private val userRepository: UserRepository,
                                                 private val schedulerProvider: SchedulerProvider) : ViewModel() {

    // region PROPERTIES

    val alarmIdMutableLiveData = MutableLiveData<Long>()

    val clockAlarmTimeConfigEvent = SingleLiveEvent<Void>()
    val clockAlarmMusicConfigEvent = SingleLiveEvent<Void>()
    val changedClockAlarmConfigEvent = SingleLiveEvent<Void>()
    val preparationClockAlarmConfigEvent = SingleLiveEvent<Void>()

    // endregion

    // region PUBLIC METHODS

    fun resume() {
    }

    fun onClockAlarmTimeConfigEvent() {

        clockAlarmTimeConfigEvent.call()
    }

    fun onClockAlarmMusicConfigEvent() {

        clockAlarmMusicConfigEvent.call()
    }

    fun onChangedClockAlarmConfigEvent() {

        changedClockAlarmConfigEvent.call()
    }


    fun onPrepareClockAlarmSettings() {

        preparationClockAlarmConfigEvent.call()
    }

    fun saveClockAlarmSettings(dateTime: Date, isEnabled: Boolean, track: Track?) {

        alarmRepository.saveAlarm(dateTime, isEnabled, track)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                        { alarmId -> alarmIdMutableLiveData.value = alarmId },
                        { _ ->  }
                )
    }

    fun saveAccessToken(accessToken: String) {

        userRepository.saveAccessToken(accessToken)
    }

    // endregion
}