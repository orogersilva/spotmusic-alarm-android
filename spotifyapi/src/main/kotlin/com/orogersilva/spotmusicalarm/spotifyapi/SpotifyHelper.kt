package com.orogersilva.spotmusicalarm.spotifyapi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import com.spotify.sdk.android.player.Config
import com.spotify.sdk.android.player.SpotifyPlayer

interface SpotifyHelper {

    // region METHODS

    fun openLoginScreen(activity: AppCompatActivity, requestCode: Int)

    fun tryPreparePlayer(activity: AppCompatActivity, resultCode: Int, data: Intent?)

    fun destroyPlayer(activity: AppCompatActivity)

    fun getAuthenticationResponse(resultCode: Int, intent: Intent?): AuthenticationResponse

    // endregion
}