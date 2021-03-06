package com.orogersilva.spotmusicalarm.base.shared

import android.support.v7.app.AppCompatActivity
import com.orogersilva.spotmusicalarm.base.SpotmusicAlarmApplication

// region ACTIVITY EXTENSIONS METHODS

fun AppCompatActivity.app(): SpotmusicAlarmApplication = application as SpotmusicAlarmApplication

// endregion

// region STRING EXTENSIONS METHODS

fun String.containsAtLeastOneNotNumericalCharacter(): Boolean = this.toLongOrNull() == null

fun String.allCharactersAreLowercase(): Boolean = this == this.toLowerCase()

// endregion