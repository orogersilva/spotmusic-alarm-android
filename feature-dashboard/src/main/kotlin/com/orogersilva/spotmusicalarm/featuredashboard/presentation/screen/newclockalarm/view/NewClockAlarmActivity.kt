package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.newclockalarm.view

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.orogersilva.spotmusicalarm.base.SpotmusicAlarmApplication
import com.orogersilva.spotmusicalarm.featuredashboard.R
import com.orogersilva.spotmusicalarm.featuredashboard.databinding.ActivityNewClockAlarmBinding
import com.orogersilva.spotmusicalarm.featuredashboard.di.component.DaggerNewClockAlarmComponent
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.BaseActivity
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.newclockalarm.NewClockAlarmViewModel
import com.orogersilva.spotmusicalarm.spotifyapi.SpotifyAdapterHelper
import javax.inject.Inject

class NewClockAlarmActivity : BaseActivity() {

    // region PROPERTIES

    private lateinit var newClockAlarmBinding: ActivityNewClockAlarmBinding

    @Inject lateinit var newClockAlarmViewModel: NewClockAlarmViewModel
    @Inject lateinit var spotifyAdapterHelper: SpotifyAdapterHelper

    private val SPOTIFY_AUTH_REQUEST_CODE = 1

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    override fun onCreate(savedInstanceState: Bundle?) {

        DaggerNewClockAlarmComponent
                .builder()
                .plus((application as SpotmusicAlarmApplication).applicationComponent)
                .inject(this)

        super.onCreate(savedInstanceState)

        prepareUi()
        prepareLogic()
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

            // spotifyAdapterHelper.tryPreparePlayer(this, resultCode, data)

            val accessToken = spotifyAdapterHelper.getAccessToken(resultCode, data)

            accessToken?.let {

                newClockAlarmViewModel.saveAccessToken(it)
            }
        }
    }

    // endregion

    // region UTILITY METHODS

    private fun prepareUi() {

        newClockAlarmBinding = DataBindingUtil.setContentView<ActivityNewClockAlarmBinding>(
                this, R.layout.activity_new_clock_alarm)
    }

    private fun prepareLogic() {

        newClockAlarmViewModel.apply {

            setClockAlarmMusicEvent.observe(this@NewClockAlarmActivity, Observer<Void> {

                spotifyAdapterHelper.openLoginScreen(this@NewClockAlarmActivity, SPOTIFY_AUTH_REQUEST_CODE)
            })
        }

        newClockAlarmBinding.setLifecycleOwner(this)
        newClockAlarmBinding.newClockAlarmViewModel = newClockAlarmViewModel
    }

    // endregion
}