package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.clockalarmmanager.view

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.orogersilva.spotmusicalarm.base.shared.app
import com.orogersilva.spotmusicalarm.dashboarddata.di.module.UserRepositoryModule
import com.orogersilva.spotmusicalarm.featuredashboard.R
import com.orogersilva.spotmusicalarm.featuredashboard.databinding.ActivityClockAlarmManagerBinding
import com.orogersilva.spotmusicalarm.featuredashboard.di.component.ClockAlarmManagerViewComponent
import com.orogersilva.spotmusicalarm.featuredashboard.di.component.DaggerDashboardComponent
import com.orogersilva.spotmusicalarm.featuredashboard.di.module.ClockAlarmManagerViewModelModule
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.BaseActivity
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.clockalarmmanager.ClockAlarmManagerViewModel
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.clockalarmmanager.adapter.ClockAlarmManagerListAdapter
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.newclockalarm.view.NewClockAlarmActivity
import kotlinx.android.synthetic.main.activity_clock_alarm_manager.*
import javax.inject.Inject

class ClockAlarmManagerActivity : BaseActivity() {

    // region PROPERTIES

    private lateinit var clockAlarmManagerViewComponent: ClockAlarmManagerViewComponent

    private lateinit var clockAlarmManagerBinding: ActivityClockAlarmManagerBinding

    @Inject lateinit var clockAlarmManagerViewModel: ClockAlarmManagerViewModel
    @Inject lateinit var clockAlarmManagerListAdapter: ClockAlarmManagerListAdapter

    // endregion

    companion object {

        // region INTENT KEYS

        val ARG_NEW_ALARM_ID = "com.orogersilva.spotmusicalarm.ARG_NEW_ALARM_ID"

        // endregion
    }

    // region ACTIVITY LIFECYCLE METHODS

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        injectDependencies()
        prepareUi()
        prepareViewModel()
    }

    override fun onResume() {

        super.onResume()

        clockAlarmManagerViewModel.resume()
    }

    // endregion

    // region UTILITY METHODS

    private fun injectDependencies() {

        val dashboardComponent = DaggerDashboardComponent.builder()
                .applicationComponent(app().applicationComponent)
                .userRepositoryModule(UserRepositoryModule())
                .build()

        clockAlarmManagerViewComponent = dashboardComponent
                .plusClockAlarmManagerViewComponent(ClockAlarmManagerViewModelModule(this))

        clockAlarmManagerViewComponent.inject(this)
    }

    private fun prepareUi() {

        clockAlarmManagerBinding = DataBindingUtil.setContentView<ActivityClockAlarmManagerBinding>(
                this, R.layout.activity_clock_alarm_manager)

        val linearLayoutManager = LinearLayoutManager(this)

        clockAlarmManagerRecyclerView.adapter = clockAlarmManagerListAdapter
        clockAlarmManagerRecyclerView.layoutManager = linearLayoutManager
    }

    private fun prepareViewModel() {

        clockAlarmManagerViewModel.apply {

            newClockAlarmLiveData.observe(this@ClockAlarmManagerActivity, Observer<Void> {
                redirectToNewClockAlarmScreen()
            })
        }

        clockAlarmManagerBinding.setLifecycleOwner(this)
        clockAlarmManagerBinding.clockAlarmManagerViewModel = clockAlarmManagerViewModel
    }

    private fun redirectToNewClockAlarmScreen() {

        val newClockAlarmIntent = Intent(this, NewClockAlarmActivity::class.java)

        startActivity(newClockAlarmIntent)
    }

    // endregion
}