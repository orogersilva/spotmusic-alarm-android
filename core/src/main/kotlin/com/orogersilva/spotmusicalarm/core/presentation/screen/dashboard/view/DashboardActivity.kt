package com.orogersilva.spotmusicalarm.core.presentation.screen.dashboard.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.orogersilva.spotmusicalarm.core.presentation.screen.dashboard.DashboardViewModel
import com.orogersilva.spotmusicalarm.core.presentation.screen.dashboard.DashboardViewModelFactory
import com.orogersilva.spotmusicalarm.core.R
import com.orogersilva.spotmusicalarm.core.databinding.ActivityDashboardBinding
import com.orogersilva.spotmusicalarm.spotifyapi.SpotifyAdapterHelper
import com.orogersilva.spotmusicalarm.spotifyapi.SpotifyHelper
import com.orogersilva.spotmusicalarm.spotifyapi.SpotifyWrapper

class DashboardActivity : AppCompatActivity() {

    // region PROPERTIES

    private lateinit var dashboardViewModel: DashboardViewModel

    private lateinit var spotifyHelper: SpotifyHelper
    private val SPOTIFY_AUTH_REQUEST_CODE = 1

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val dashboardBinding = DataBindingUtil.setContentView<ActivityDashboardBinding>(
                this, R.layout.activity_dashboard)

        val dashboardViewModelFactory = DashboardViewModelFactory()

        dashboardViewModel = ViewModelProviders.of(this, dashboardViewModelFactory)
                .get(DashboardViewModel::class.java)

        dashboardViewModel.apply {

            newClockAlarmEvent.observe(this@DashboardActivity, Observer<Void> {
                redirectToCreateClockAlarmScreen()
            })
        }

        dashboardBinding.setLifecycleOwner(this)
        dashboardBinding.dashboardViewModel = dashboardViewModel

        spotifyHelper = SpotifyAdapterHelper(this, SpotifyWrapper())

        spotifyHelper.openLoginScreen(SPOTIFY_AUTH_REQUEST_CODE)
    }

    override fun onDestroy() {

        spotifyHelper.destroyPlayer()

        super.onDestroy()
    }

    // endregion

    // region OVERRIDED METHODS

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SPOTIFY_AUTH_REQUEST_CODE) {

            spotifyHelper.tryPreparePlayer(resultCode, data)
        }
    }

    // endregion

    // region UTILITY METHODS

    private fun redirectToCreateClockAlarmScreen() {

        // TODO: Redirect to creation of clock alarm screen.
    }

    // endregion
}