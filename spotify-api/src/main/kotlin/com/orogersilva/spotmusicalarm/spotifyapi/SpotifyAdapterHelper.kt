package com.orogersilva.spotmusicalarm.spotifyapi

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import com.spotify.sdk.android.player.*
import javax.inject.Inject

class SpotifyAdapterHelper @Inject constructor(private val spotifyWrapper: SpotifyWrapper)
    : SpotifyHelper, ConnectionStateCallback, Player.NotificationCallback {

    // region PROPERTIES

    private lateinit var spotifyPlayer: SpotifyPlayer

    private val SPOTIFY_CLIENT_ID = BuildConfig.SPOTIFY_CLIENT_ID
    private val SPOTIFY_REDIRECT_URI = BuildConfig.SPOTIFY_REDIRECT_URI

    // endregion

    // region OVERRIDED METHODS

    override fun openLoginScreen(activity: AppCompatActivity, requestCode: Int) {

        val spotifyAuthRequest = AuthenticationRequest
                .Builder(SPOTIFY_CLIENT_ID, AuthenticationResponse.Type.TOKEN, SPOTIFY_REDIRECT_URI)
                .setScopes(arrayOf(
                        "playlist-read-collaborative",
                        "playlist-read-private",
                        "streaming"
                ))
                .build()

        spotifyWrapper.openLoginActivity(activity, requestCode, spotifyAuthRequest)
    }

    override fun tryPreparePlayer(activity: AppCompatActivity, resultCode: Int, data: Intent?) {

        val authResponse = getAuthenticationResponse(resultCode, data)

        if (authResponse.type == AuthenticationResponse.Type.TOKEN) {
            preparePlayer(activity, authResponse.accessToken)
        }
    }

    override fun destroyPlayer(activity: AppCompatActivity) {

        spotifyWrapper.destroyPlayer(activity)
    }

    override fun getAccessToken(resultCode: Int, data: Intent?): String? {

        val authResponse = getAuthenticationResponse(resultCode, data)

        return if (authResponse.type == AuthenticationResponse.Type.TOKEN)
            authResponse.accessToken else null
    }

    override fun onLoggedOut() {
    }

    override fun onLoggedIn() {

        spotifyPlayer.playUri(null, "spotify:track:2TpxZ7JUBn3uw46aR7qd6V",
                0, 0)
    }

    override fun onConnectionMessage(message: String) {
    }

    override fun onLoginFailed(error: Error) {
    }

    override fun onTemporaryError() {
    }

    override fun onPlaybackError(error: Error) {

        /*when (error) {

            else -> {

            }
        }*/
    }

    override fun onPlaybackEvent(playerEvent: PlayerEvent) {

        /*when (playerEvent) {

            else -> {

            }
        }*/
    }

    // endregion

    // region UTILITY METHODS

    private fun preparePlayer(activity: AppCompatActivity, accessToken: String) {

        val spotifyPlayerConfig = Config(activity as Context, accessToken, SPOTIFY_CLIENT_ID)

        spotifyWrapper.getPlayer(spotifyPlayerConfig, activity, object : SpotifyPlayer.InitializationObserver {

            override fun onInitialized(spotPlayer: SpotifyPlayer) {

                spotifyPlayer = spotPlayer

                spotifyPlayer.addConnectionStateCallback(this@SpotifyAdapterHelper)
                spotifyPlayer.addNotificationCallback(this@SpotifyAdapterHelper)
            }

            override fun onError(throwable: Throwable) {
            }
        })
    }

    private fun getAuthenticationResponse(resultCode: Int, intent: Intent?): AuthenticationResponse =
            spotifyWrapper.getResponse(resultCode, intent)

    // endregion
}