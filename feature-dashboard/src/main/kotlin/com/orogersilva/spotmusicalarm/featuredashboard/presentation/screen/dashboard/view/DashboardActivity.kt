package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.dashboard.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.orogersilva.spotmusicalarm.base.SpotmusicAlarmApplication
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.dashboard.DashboardViewModel
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.dashboard.DashboardViewModelFactory
import com.orogersilva.spotmusicalarm.featuredashboard.R
import com.orogersilva.spotmusicalarm.featuredashboard.databinding.ActivityDashboardBinding
import com.orogersilva.spotmusicalarm.featuredashboard.di.component.DaggerDashboardComponent
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.BaseActivity
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.newclockalarm.view.NewClockAlarmActivity
import com.orogersilva.spotmusicalarm.spotifyapi.SpotifyAdapterHelper
import javax.inject.Inject

class DashboardActivity : BaseActivity() {

    // region PROPERTIES

    @Inject lateinit var dashboardViewModel: DashboardViewModel

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
    }

    // endregion

    // region UTILITY METHODS

    private fun redirectToCreateClockAlarmScreen() {

        val newClockAlarmIntent = Intent(this, NewClockAlarmActivity::class.java)

        startActivity(newClockAlarmIntent)
    }

    // endregion
}