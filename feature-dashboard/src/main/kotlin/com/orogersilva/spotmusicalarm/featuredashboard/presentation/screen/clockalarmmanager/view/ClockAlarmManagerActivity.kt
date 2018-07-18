package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.clockalarmmanager.view

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.orogersilva.spotmusicalarm.base.SpotmusicAlarmApplication
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.clockalarmmanager.ClockAlarmManagerViewModel
import com.orogersilva.spotmusicalarm.featuredashboard.R
import com.orogersilva.spotmusicalarm.featuredashboard.databinding.ActivityClockAlarmManagerBinding
import com.orogersilva.spotmusicalarm.featuredashboard.di.component.DaggerClockAlarmManagerComponent
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.BaseActivity
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.clockalarmmanager.adapter.ClockAlarmManagerListAdapter
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.newclockalarm.view.NewClockAlarmActivity
import kotlinx.android.synthetic.main.activity_clock_alarm_manager.*
import javax.inject.Inject

class ClockAlarmManagerActivity : BaseActivity() {

    // region PROPERTIES

    private lateinit var clockAlarmManagerBinding: ActivityClockAlarmManagerBinding

    @Inject lateinit var clockAlarmManagerViewModel: ClockAlarmManagerViewModel
    @Inject lateinit var clockAlarmManagerListAdapter: ClockAlarmManagerListAdapter

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    override fun onCreate(savedInstanceState: Bundle?) {

        DaggerClockAlarmManagerComponent
                .builder()
                .plus((application as SpotmusicAlarmApplication).applicationComponent)
                .inject(this)

        super.onCreate(savedInstanceState)

        prepareUi()
        prepareViewModel()
    }

    // endregion

    // region UTILITY METHODS

    private fun prepareUi() {

        clockAlarmManagerBinding = DataBindingUtil.setContentView<ActivityClockAlarmManagerBinding>(
                this, R.layout.activity_clock_alarm_manager)

        val linearLayoutManager = LinearLayoutManager(this)

        clockAlarmManagerRecyclerView.adapter = clockAlarmManagerListAdapter
        clockAlarmManagerRecyclerView.layoutManager = linearLayoutManager
    }

    private fun prepareViewModel() {

        clockAlarmManagerViewModel.apply {

            newClockAlarmEvent.observe(this@ClockAlarmManagerActivity, Observer<Void> {
                redirectToCreateClockAlarmScreen()
            })
        }

        clockAlarmManagerBinding.setLifecycleOwner(this)
        clockAlarmManagerBinding.clockAlarmManagerViewModel = clockAlarmManagerViewModel
    }

    private fun redirectToCreateClockAlarmScreen() {

        val newClockAlarmIntent = Intent(this, NewClockAlarmActivity::class.java)

        startActivity(newClockAlarmIntent)
    }

    // endregion
}