package com.orogersilva.spotmusicalarm.spotifyapi

import android.content.Intent
import android.support.v7.app.AppCompatActivity

interface SpotifyHelper {

    // region METHODS

    fun openLoginScreen(activity: AppCompatActivity, requestCode: Int)

    fun tryPreparePlayer(activity: AppCompatActivity, resultCode: Int, data: Intent?)

    fun destroyPlayer(activity: AppCompatActivity)

    fun getAccessToken(resultCode: Int, data: Intent?): String?

    // endregion
}