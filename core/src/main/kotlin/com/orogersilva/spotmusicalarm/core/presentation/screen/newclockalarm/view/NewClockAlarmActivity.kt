package com.orogersilva.spotmusicalarm.core.presentation.screen.newclockalarm.view

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.orogersilva.spotmusicalarm.base.SpotmusicAlarmApplication
import com.orogersilva.spotmusicalarm.core.R
import com.orogersilva.spotmusicalarm.core.databinding.ActivityNewclockalarmBinding
import com.orogersilva.spotmusicalarm.core.di.component.DaggerNewClockAlarmComponent
import com.orogersilva.spotmusicalarm.core.presentation.screen.BaseActivity
import com.orogersilva.spotmusicalarm.core.presentation.screen.newclockalarm.NewClockAlarmViewModel
import com.orogersilva.spotmusicalarm.spotifyapi.SpotifyAdapterHelper
import javax.inject.Inject

class NewClockAlarmActivity : BaseActivity() {

    // region PROPERTIES

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

        val newClockAlarmBinding = DataBindingUtil.setContentView<ActivityNewclockalarmBinding>(
                this, R.layout.activity_newclockalarm)

        newClockAlarmViewModel.apply {

            setClockAlarmMusicEvent.observe(this@NewClockAlarmActivity, Observer<Void> {

                spotifyAdapterHelper.openLoginScreen(this@NewClockAlarmActivity, SPOTIFY_AUTH_REQUEST_CODE)
            })
        }

        newClockAlarmBinding.setLifecycleOwner(this)
        newClockAlarmBinding.newClockAlarmViewModel = newClockAlarmViewModel
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
}