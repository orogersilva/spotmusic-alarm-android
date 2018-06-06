package com.orogersilva.spotmusicalarm.base

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import com.spotify.sdk.android.player.Config
import com.spotify.sdk.android.player.SpotifyPlayer

interface SpotifyHelper {

    // region METHODS

    fun openLoginScreen(requestCode: Int)

    fun getPlayer(accessToken: String)

    fun destroyPlayer()

    fun getAuthenticationResponse(resultCode: Int, intent: Intent?): AuthenticationResponse

    // endregion
}