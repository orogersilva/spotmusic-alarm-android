package com.orogersilva.spotmusicalarm

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import com.spotify.sdk.android.player.*

class MainActivity : AppCompatActivity(), Player.NotificationCallback, ConnectionStateCallback {

    // region PROPERTIES

    private val SPOTIFY_CLIENT_ID = BuildConfig.SPOTIFY_CLIENT_ID
    private val SPOTIFY_REDIRECT_URI = BuildConfig.SPOTIFY_REDIRECT_URI

    private val SPOTIFY_AUTH_REQUEST_CODE = 1

    private lateinit var spotifyPlayer: SpotifyPlayer

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spotifyAuthRequest = AuthenticationRequest
                .Builder(SPOTIFY_CLIENT_ID, AuthenticationResponse.Type.TOKEN, SPOTIFY_REDIRECT_URI)
                .setScopes(arrayOf("streaming"))
                .build()

        AuthenticationClient.openLoginActivity(this, SPOTIFY_AUTH_REQUEST_CODE,
                spotifyAuthRequest)
    }

    override fun onDestroy() {

        Spotify.destroyPlayer(this)

        super.onDestroy()
    }

    // endregion

    // region OVERRIDED METHODS

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SPOTIFY_AUTH_REQUEST_CODE) {

            val authResponse = AuthenticationClient.getResponse(resultCode, data)

            if (authResponse.type == AuthenticationResponse.Type.TOKEN) {

                val spotifyPlayerConfig = Config(this, authResponse.accessToken,
                        SPOTIFY_CLIENT_ID)

                Spotify.getPlayer(spotifyPlayerConfig, this, object : SpotifyPlayer.InitializationObserver {

                    override fun onInitialized(spotPlayer: SpotifyPlayer) {

                        spotifyPlayer = spotPlayer

                        spotifyPlayer.addConnectionStateCallback(this@MainActivity)
                        spotifyPlayer.addNotificationCallback(this@MainActivity)
                    }

                    override fun onError(throwable: Throwable) {
                    }
                })
            }
        }
    }

    override fun onPlaybackEvent(playerEvent: PlayerEvent) {

        /*when (playerEvent) {

            else -> {

            }
        }*/
    }

    override fun onPlaybackError(error: Error) {

        /*when (error) {

            else -> {

            }
        }*/
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

    // endregion
}