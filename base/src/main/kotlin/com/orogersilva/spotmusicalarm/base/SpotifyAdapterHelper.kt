package com.orogersilva.spotmusicalarm.base

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import com.spotify.sdk.android.player.*

class SpotifyAdapterHelper(private val activity: AppCompatActivity) : SpotifyHelper, ConnectionStateCallback, Player.NotificationCallback {

    // region PROPERTIES

    private val SPOTIFY_CLIENT_ID = BuildConfig.SPOTIFY_CLIENT_ID
    private val SPOTIFY_REDIRECT_URI = BuildConfig.SPOTIFY_REDIRECT_URI

    private lateinit var spotifyPlayer: SpotifyPlayer

    // endregion

    // region OVERRIDED METHODS

    override fun openLoginScreen(requestCode: Int) {

        val spotifyAuthRequest = AuthenticationRequest
                .Builder(SPOTIFY_CLIENT_ID, AuthenticationResponse.Type.TOKEN, SPOTIFY_REDIRECT_URI)
                .setScopes(arrayOf("streaming"))
                .build()

        AuthenticationClient.openLoginActivity(activity, requestCode, spotifyAuthRequest)
    }

    override fun getPlayer(accessToken: String) {

        val spotifyPlayerConfig = Config(activity, accessToken, SPOTIFY_CLIENT_ID)

        Spotify.getPlayer(spotifyPlayerConfig, activity, object : SpotifyPlayer.InitializationObserver {

            override fun onInitialized(spotPlayer: SpotifyPlayer) {

                spotifyPlayer = spotPlayer

                spotifyPlayer.addConnectionStateCallback(this@SpotifyAdapterHelper)
                spotifyPlayer.addNotificationCallback(this@SpotifyAdapterHelper)
            }

            override fun onError(throwable: Throwable) {
            }
        })
    }

    override fun destroyPlayer() {

        Spotify.destroyPlayer(activity)
    }

    override fun getAuthenticationResponse(resultCode: Int, intent: Intent?): AuthenticationResponse =
            AuthenticationClient.getResponse(resultCode, intent)

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
}