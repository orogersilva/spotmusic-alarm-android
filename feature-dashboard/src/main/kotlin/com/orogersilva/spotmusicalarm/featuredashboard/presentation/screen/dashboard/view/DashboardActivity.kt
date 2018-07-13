package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.dashboard.view

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.orogersilva.spotmusicalarm.base.SpotmusicAlarmApplication
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.dashboard.DashboardViewModel
import com.orogersilva.spotmusicalarm.featuredashboard.R
import com.orogersilva.spotmusicalarm.featuredashboard.databinding.ActivityDashboardBinding
import com.orogersilva.spotmusicalarm.featuredashboard.di.component.DaggerDashboardComponent
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.BaseActivity
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.dashboard.adapter.DashboardListAdapter
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.newclockalarm.view.NewClockAlarmActivity
import kotlinx.android.synthetic.main.activity_dashboard.*
import javax.inject.Inject

class DashboardActivity : BaseActivity() {

    // region PROPERTIES

    private lateinit var dashboardBinding: ActivityDashboardBinding

    @Inject lateinit var dashboardViewModel: DashboardViewModel
    @Inject lateinit var dashboardListAdapter: DashboardListAdapter

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    override fun onCreate(savedInstanceState: Bundle?) {

        DaggerDashboardComponent
                .builder()
                .plus((application as SpotmusicAlarmApplication).applicationComponent)
                .inject(this)

        super.onCreate(savedInstanceState)

        prepareUi()
        prepareLogic()
    }

    // endregion

    // region UTILITY METHODS

    private fun prepareUi() {

        dashboardBinding = DataBindingUtil.setContentView<ActivityDashboardBinding>(
                this, R.layout.activity_dashboard)

        val linearLayoutManager = LinearLayoutManager(this)

        dashboardRecyclerView.adapter = dashboardListAdapter
        dashboardRecyclerView.layoutManager = linearLayoutManager
    }

    private fun prepareLogic() {

        dashboardViewModel.apply {

            newClockAlarmEvent.observe(this@DashboardActivity, Observer<Void> {
                redirectToCreateClockAlarmScreen()
            })
        }

        dashboardBinding.setLifecycleOwner(this)
        dashboardBinding.dashViewModel = dashboardViewModel
    }

    private fun redirectToCreateClockAlarmScreen() {

        val newClockAlarmIntent = Intent(this, NewClockAlarmActivity::class.java)

        startActivity(newClockAlarmIntent)
    }

    // endregion
}