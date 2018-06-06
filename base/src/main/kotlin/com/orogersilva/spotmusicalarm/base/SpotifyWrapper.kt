package com.orogersilva.spotmusicalarm.base

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import com.spotify.sdk.android.player.Config
import com.spotify.sdk.android.player.Spotify
import com.spotify.sdk.android.player.SpotifyPlayer

class SpotifyWrapper {

    // region PUBLIC METHODS

    // AuthenticationClient.openLoginActivity(activity, requestCode, spotifyAuthRequest)

    fun openLoginActivity(activity: AppCompatActivity, requestCode: Int, spotifyAuthenticationRequest: AuthenticationRequest) {

        AuthenticationClient.openLoginActivity(activity, requestCode, spotifyAuthenticationRequest)
    }

    fun getResponse(resultCode: Int, intent: Intent?): AuthenticationResponse =
            AuthenticationClient.getResponse(resultCode, intent)

    fun getPlayer(spotifyPlayerConfig: Config, activity: AppCompatActivity, observer: SpotifyPlayer.InitializationObserver) {

        Spotify.getPlayer(spotifyPlayerConfig, activity, observer)
    }

    fun destroyPlayer(activity: AppCompatActivity) {

        Spotify.destroyPlayer(activity)
    }

    // endregion
}