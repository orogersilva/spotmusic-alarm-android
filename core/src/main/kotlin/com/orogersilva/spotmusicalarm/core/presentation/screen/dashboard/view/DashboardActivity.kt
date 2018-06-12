package com.orogersilva.spotmusicalarm.core.presentation.screen.dashboard.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.orogersilva.spotmusicalarm.base.SpotmusicAlarmApplication
import com.orogersilva.spotmusicalarm.core.presentation.screen.dashboard.DashboardViewModel
import com.orogersilva.spotmusicalarm.core.presentation.screen.dashboard.DashboardViewModelFactory
import com.orogersilva.spotmusicalarm.core.R
import com.orogersilva.spotmusicalarm.core.databinding.ActivityDashboardBinding
import com.orogersilva.spotmusicalarm.core.di.component.DaggerDashboardComponent
import com.orogersilva.spotmusicalarm.core.presentation.screen.BaseActivity
import com.orogersilva.spotmusicalarm.spotifyapi.SpotifyAdapterHelper
import javax.inject.Inject

class DashboardActivity : BaseActivity() {

    // region PROPERTIES

    @Inject lateinit var dashboardViewModel: DashboardViewModel
    @Inject lateinit var spotifyAdapterHelper: SpotifyAdapterHelper

    private val SPOTIFY_AUTH_REQUEST_CODE = 1

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    override fun onCreate(savedInstanceState: Bundle?) {

        DaggerDashboardComponent
                .builder()
                .plus((application as SpotmusicAlarmApplication).applicationComponent)
                .inject(this)

        super.onCreate(savedInstanceState)

        val dashboardBinding = DataBindingUtil.setContentView<ActivityDashboardBinding>(
                this, R.layout.activity_dashboard)

        dashboardViewModel.apply {

            newClockAlarmEvent.observe(this@DashboardActivity, Observer<Void> {
                redirectToCreateClockAlarmScreen()
            })
        }

        dashboardBinding.setLifecycleOwner(this)
        dashboardBinding.dashViewModel = dashboardViewModel

        spotifyAdapterHelper.openLoginScreen(this, SPOTIFY_AUTH_REQUEST_CODE)
    }

    override fun onDestroy() {

        spotifyAdapterHelper.destroyPlayer(this)

        super.onDestroy()
    }

    // endregion

    // region OVERRIDED METHODS

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SPOTIFY_AUTH_REQUEST_CODE) {

            spotifyAdapterHelper.tryPreparePlayer(this, resultCode, data)
        }
    }

    // endregion

    // region UTILITY METHODS

    private fun redirectToCreateClockAlarmScreen() {

        // TODO: Redirect to creation of clock alarm screen.
    }

    // endregion
}